
package com.sl.usuario;


public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private String email;
    
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getSenha(){
        return this.senha;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setAll(String nome, String senha, String email){
        this.setNome(nome);
        this.setSenha(senha);
        this.setEmail(email);
    }
    public String[] getAll(){
        String[] lista = new String[3];
        lista[0] = this.nome;
        lista[1] = this.senha;
        lista[2] = this.email;
        return lista;
    }
}
