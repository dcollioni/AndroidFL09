package br.edu.qi.cadastroestados;

public class Estado {
	private String nome;
	private String sigla;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public Estado(String nome, String sigla) {
		super();
		this.nome = nome;
		this.sigla = sigla;
	}
	
	@Override
	public String toString() {
		return nome + " (" + sigla + ")";
	}
}
