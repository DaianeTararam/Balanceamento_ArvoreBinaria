package controller;

import model.estrutura.arvore.ArvoreBinaria;
import model.estrutura.lista.ListaEncadeadaSimples;

public class ArvoreBinariaController{
	public ArvoreBinariaController(){
		super();
	}
	public String teste() throws Exception{
		ArvoreBinaria arvore = new ArvoreBinaria();
		arvore.add(46);
		arvore.add(45);
		arvore.add(41);
		arvore.add(40);
		arvore.add(36);
		arvore.add(35);
		arvore.add(31);
		arvore.add(30);
		arvore.add(29);
		arvore.add(28);
		arvore.add(27);
		arvore.add(25);
		arvore.add(24);
		arvore.add(23);
		arvore.add(22);

		ListaEncadeadaSimples lista = arvore.preOrdem();
		return lista.toString();
	}
}