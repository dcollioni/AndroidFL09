package br.edu.qi.contatos;

import java.io.Serializable;

public class Contato implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String telefone;
	private String email;
	private String cidade;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public Contato(String nome, String telefone, String email, String cidade) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.cidade = cidade;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	
}
