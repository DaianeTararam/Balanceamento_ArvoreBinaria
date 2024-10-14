package model.estrutura.arvore;

import model.estrutura.arvore.No;
import model.estrutura.lista.ListaEncadeadaSimples;

public class ArvoreBinaria<T extends Comparable<T> >{

	private No<T> raiz;
	private int tamanho = 0;

	public ArvoreBinaria(){
		this.raiz = null;
	}

	public void add(T valor){
		No<T> novo = new No<T> (valor);
		tamanho++;
		if (raiz == null){
			this.raiz = novo;
			defineBalanceamento(this.raiz);
			return;
		}
		
		No<T> atual = this.raiz;
		while(true){
			if (novo.getValor().compareTo(atual.getValor()) == -1){
				if(atual.getMenor() != null){
					atual = atual.getMenor();
				}else{
					atual.setMenor(novo);
					novo.setRaiz(atual);
					defineBalanceamento(this.raiz);
					this.raiz = balancear(raiz);
					break;
				}
			}else{
				if(atual.getMaior() != null){
					atual = atual.getMaior();
				}else{
					atual.setMaior(novo);
					novo.setRaiz(atual);
					defineBalanceamento(this.raiz);
					this.raiz = balancear(raiz);
					break;
				}
			}
		}
	}

	public int altura(No<T> atual){
		if(atual == null){
			return -1;
		}
		if(atual.getMaior() == null && atual.getMenor() == null){
			return 0;
		}else if(atual.getMenor() == null){
			return 1 + altura(atual.getMaior());
		}else if(atual.getMaior() == null){
			return 1 + altura(atual.getMenor());
		}else{
			if(altura(atual.getMenor()) > altura(atual.getMaior())){
				return 1 + altura(atual.getMenor());
			}else{
				return 1 + altura(atual.getMaior());
			}
		}
	}

	public void defineBalanceamento(No<T> atual){
		atual.setBalanceamento(altura(atual.getMenor()) - altura(atual.getMaior()));
		if(atual.getMaior() != null)
			defineBalanceamento(atual.getMaior());
		if(atual.getMenor() != null)
			defineBalanceamento(atual.getMenor());
	}

	public No<T> rotacaoEsquerda(No<T> atual){
		No aux = atual.getMaior();
		aux.setRaiz(atual.getRaiz());
		if(aux.getMenor() != null)
			aux.getMenor().setRaiz(atual);
		atual.setRaiz(aux);
		atual.setMaior(aux.getMenor());
		aux.setMenor(atual);
		
		if(aux.getRaiz() != null){
			if(aux.getRaiz().getMaior() == atual){
				aux.getRaiz().setMaior(aux);
			} else if(aux.getRaiz().getMenor() == atual){
				aux.getRaiz().setMenor(aux);
			}
		}
		defineBalanceamento(aux);
		return aux;
	}

	public No<T> rotacaoDireita(No<T> atual){
		No aux = atual.getMenor();
		aux.setRaiz(atual.getRaiz());
		if(aux.getMaior() != null)
			aux.getMaior().setRaiz(atual);
		atual.setRaiz(aux);
		atual.setMenor(aux.getMaior());
		aux.setMaior(atual);
		
		if(aux.getRaiz() != null){
			if(aux.getRaiz().getMaior() == atual){
				aux.getRaiz().setMaior(aux);
			} else if(aux.getRaiz().getMenor() == atual){
				aux.getRaiz().setMenor(aux);
			}
		}
		defineBalanceamento(aux);
		return aux;
	}

	public No rotacaoDuplaDireita(No<T> atual){
		No<T> aux = atual.getMenor();
		atual.setMenor(rotacaoEsquerda(aux));
		No<T> aux2 = rotacaoDireita(atual);
		return aux2;
	}
	
	public No rotacaoDuplaEsquerda(No<T> atual){
		No<T> aux = atual.getMaior();
		atual.setMaior(rotacaoDireita(aux));
		No<T> aux2 = rotacaoEsquerda(atual);
		return aux2;
	}

	public void balancear(){
		this.raiz = balancear(this.raiz);
	}

	public No<T> balancear(No<T> atual){
		if(atual.getBalanceamento() == 2 && atual.getMaior().getBalanceamento() >= 0){
			atual = rotacaoDireita(atual);
		}else if(atual.getBalanceamento() == -2 && atual.getMaior().getBalanceamento() <= 0){
			atual = rotacaoEsquerda(atual);
		}else if(atual.getBalanceamento() == 2 && atual.getMenor().getBalanceamento() < 0){
			atual = rotacaoDuplaDireita(atual);
		}else if(atual.getBalanceamento() == -2 && atual.getMenor().getBalanceamento() > 0){
			atual = rotacaoDuplaEsquerda(atual);
		}
		if(atual.getMaior() != null){
			balancear(atual.getMaior());
		}
		if(atual.getMenor() != null){
			balancear(atual.getMenor());
		}
		return atual;
	}

		
		

	public ListaEncadeadaSimples ordem(){
		ListaEncadeadaSimples lista = new ListaEncadeadaSimples();
		ordem(this.raiz, lista);
		return lista;
	}

	public ListaEncadeadaSimples preOrdem(){
		ListaEncadeadaSimples lista = new ListaEncadeadaSimples();
		ordem(this.raiz, lista);
		return lista;
	}

	public ListaEncadeadaSimples posOrdem(){
		ListaEncadeadaSimples lista = new ListaEncadeadaSimples();
		ordem(this.raiz, lista);
		return lista;
	}

	private void ordem(No<T> atual, ListaEncadeadaSimples lista){
		if(atual != null){
			ordem(atual.getMenor(), lista);
			lista.append(atual.getValor());
			ordem(atual.getMaior(), lista);
		}
	}

	private void preOrdem(No<T> atual, ListaEncadeadaSimples lista){
		if(atual != null){
			lista.append(atual.getValor());
			preOrdem(atual.getMenor(), lista);
			preOrdem(atual.getMaior(), lista);
		}
	}
	
	private void posOrdem(No<T> atual, ListaEncadeadaSimples lista){
		if(atual != null){
			posOrdem(atual.getMenor(), lista);
			posOrdem(atual.getMaior(), lista);
			lista.append(atual.getValor());
			
		}
	}

	public boolean remover(T valor){
		No<T> atual = this.raiz;
		No<T> paiAtual = null;
		while(atual != null){
			if (atual.getValor().equals(valor)){
				break;
			}else if (valor.compareTo(atual.getValor()) < 0){
				paiAtual = atual;
				atual = atual.getMenor();
			}else{
				paiAtual = atual;
				atual = atual.getMaior();
			}
		}

		if(atual == null) return false;
		if(atual.getMaior() != null){
			No<T> substituto = atual.getMaior();
			No<T> paiSubstituto = atual;
			while(substituto.getMenor() != null){
				paiSubstituto = substituto;
				substituto = substituto.getMenor();
			}
			substituto.setMenor(atual.getMenor());
			if(paiAtual != null){
				if(atual.getValor().compareTo(paiAtual.getValor()) == -1){
					paiAtual.setMenor(substituto);
				}else{
					paiAtual.setMaior(substituto);
				}
			} else{
				this.raiz = substituto;
				paiSubstituto.setMenor(null);
				this.raiz.setMaior(paiSubstituto);
				this.raiz.setMenor(atual.getMenor());
			}
			if (substituto.getValor().compareTo(paiSubstituto.getValor()) == - 1){
				paiSubstituto.setMenor(null);
				substituto.setMaior(paiSubstituto);
			}else{
				paiSubstituto.setMaior(null);
			}
		}else if(atual.getMenor() != null){
			No<T> substituto = atual.getMenor();
			No<T> paiSubstituto = atual;
			while(substituto.getMaior() != null){
				paiSubstituto = substituto;
				substituto = substituto.getMaior();
			}
			if(paiAtual != null){
				if (atual.getValor().compareTo(paiAtual.getValor()) == -1){
					paiAtual.setMenor(substituto);
				}else{
					paiAtual.setMaior(substituto);
				}
			}else{
				this.raiz = substituto;
			}
	
			if(substituto.getValor().compareTo(paiSubstituto.getValor()) == -1){
				paiAtual.setMenor(null);
			}else{
				paiAtual.setMaior(null);
			}
		} else{
			if (paiAtual != null){
				if(atual.getValor().compareTo(paiAtual.getValor()) == -1){
					paiAtual.setMenor(null);
				}else{
					paiAtual.setMaior(null);
				}
			}else{
				this.raiz = null;
			}
		}
		return true;
	}
}
			