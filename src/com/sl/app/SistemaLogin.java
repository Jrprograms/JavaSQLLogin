package com.sl.app;

import com.sl.dao.UsuarioDAO;
import com.sl.encrypt.Cripto;
import com.sl.usuario.Usuario;

import java.util.Scanner;

public class SistemaLogin {

    
    
    public static void main(String[] args) {
        System.out.println("O que você deseja fazer?");
        System.out.println("1- Cadastrar   2-Entrar");
        System.out.print(">");
        
        Scanner leitor = new Scanner(System.in);
        int resposta = leitor.nextInt();
        
        if(resposta == 1){
            cadastrar();
        }else{
            entrar();
        }
        
    }
    
    public static void cadastrar(){
        Usuario u = new Usuario();
        
        Scanner leitor = new Scanner(System.in);
        
        System.out.println("Digite os dados:");
        System.out.println("Nome>");
        String nome = leitor.next();
        System.out.println("Senha>");
        String senha = leitor.next();
        System.out.println("Email>");
        String email = leitor.next();
        u.setAll(nome,senha,email);
        
        UsuarioDAO bd = new UsuarioDAO();
        
        bd.insert(u);
    }

    private static void entrar() {
        Usuario u = new Usuario();
        UsuarioDAO bd = new UsuarioDAO();
        
        Scanner leitor = new Scanner(System.in);
        
        System.out.println("Nome do Usuário:");
        u.setNome(leitor.next());
        System.out.println("Senha:");
        u.setSenha(leitor.next());
        
        if(bd.login(u)){
            System.out.println("Logado com sucesso");
        }else{
            System.out.println("Senha incorreta");
        }
        
    }
    
}
