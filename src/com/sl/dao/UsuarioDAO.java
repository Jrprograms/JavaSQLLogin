package com.sl.dao;

import com.sl.encrypt.Cripto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sl.factory.ConexaoFactory;
import com.sl.usuario.Usuario;



public class UsuarioDAO {
	// CRUD - Create, Read, Update e Delete
	
        Connection con = null;
        PreparedStatement pstm = null;
        boolean redirecionamento = false;
                
	// Método para inserir os dados no bd
	public void insert(Usuario usuario) {
		// Variável para registrar a consulta
		String query = "INSERT INTO usuario (nome,senha, email) VALUES (? , ?, ?)";
				
		try {

                        setConexaoEPstm(query);
                        
                        String[] dados = usuario.getAll();
 
                        pstm.setString(1,dados[0]);
                        pstm.setString(2,Cripto.encrypt(dados[1]));
                        pstm.setString(3,dados[2]);
                        
                        pstm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}//Garantindo que a conexão será finalizada
                finally{
                    try{
                        finally_exception();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
		
	}
        //Funcao de login
        public boolean login(Usuario u){
        
            Usuario uBD = getSenhaBD(u);
            System.out.println(uBD.getSenha());
            System.out.println(Cripto.encrypt(u.getSenha()));
            return uBD.getSenha().equals(Cripto.encrypt(u.getSenha()));
            
        }
        
        //Pegar senha no banco de dados
        public Usuario getSenhaBD(Usuario u){
            String query = "SELECT senha from usuario where nome like '" + u.getNome()+ "';";
            String resposta = null;
            Usuario uBD = new Usuario();
            try{
                setConexaoEPstm(query);
                ResultSet rset = null;
                rset = pstm.executeQuery();
                while(rset.next()){
                    uBD.setSenha(rset.getString("senha"));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            
            return uBD;
        }
        
        //novas funções
        private void finally_exception(){
            try{
                if(pstm != null){
                    pstm.close();
                }
                if(con!=null){
                    con.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        //funcao para definir as variaveis con e pstm
        private void setConexaoEPstm(String query){
            //verifica se já existe uma conexão
            if(con == null){
                try {
                    con = ConexaoFactory.createConnectionToMySQL();
                    pstm = con.prepareStatement(query); 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                //ja existe uma conexão
            }
            
        }
        
}
