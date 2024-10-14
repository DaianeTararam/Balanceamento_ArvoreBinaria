package model.estrutura.arvore;

public class No<T> {

	private T valor;
	private No<T> maior;
	private No<T> menor;
	private No<T> raiz;
	private int balanceamento;

	public No(T valor) {
		this.maior = null;
		this.menor = null;
		this.raiz = null;
		this.balanceamento = 0;
		this.valor = valor;
	}

	public T getValor(){
		return valor;
	}

	public void setValor(T valor){
		this.valor = valor;
	}

	public No<T> getMaior(){
		return maior;
	}
	
	public void setMaior(No<T> maior){
		this.maior = maior;
	}
	public No<T> getMenor(){
		return menor;
	}
	public void setMenor(No<T> menor){
		this.menor = menor;
	}

	public int getBalanceamento(){
		return this.balanceamento;
	}

	public void setBalanceamento(int balanceamento){
		this.balanceamento = balanceamento;
	}

	public No<T> getRaiz(){
		return raiz;
	}

	public void setRaiz(No<T> raiz){
		this.raiz = raiz;
	}

	public String toString(){
		return valor.toString();
	}
}

