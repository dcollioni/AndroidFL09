package br.edu.qi.cadastroconta;

public class Conta {
	private String tipo;
	private int numero;
	private boolean cartaoCredito;
	private boolean talaoCheque;
	private boolean seguroCartao;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public boolean isCartaoCredito() {
		return cartaoCredito;
	}
	public void setCartaoCredito(boolean cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
	public boolean isTalaoCheque() {
		return talaoCheque;
	}
	public void setTalaoCheque(boolean talaoCheque) {
		this.talaoCheque = talaoCheque;
	}
	public boolean isSeguroCartao() {
		return seguroCartao;
	}
	public void setSeguroCartao(boolean seguroCartao) {
		this.seguroCartao = seguroCartao;
	}
	
	@Override
	public String toString() {
		return "Conta [tipo=" + tipo + ", numero=" + numero
				+ ", cartaoCredito=" + cartaoCredito + ", talaoCheque="
				+ talaoCheque + ", seguroCartao=" + seguroCartao + "]";
	}
	
} // fecha classe
