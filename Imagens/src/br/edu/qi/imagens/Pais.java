package br.edu.qi.imagens;

public class Pais {
	private String nome;
	private int bandeira;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getBandeira() {
		return bandeira;
	}
	public void setBandeira(int bandeira) {
		this.bandeira = bandeira;
	}
	
	public Pais(String nome, int bandeira) {
		super();
		this.nome = nome;
		this.bandeira = bandeira;
	}
	
	@Override
	public String toString() {
		return nome;
	}
}
