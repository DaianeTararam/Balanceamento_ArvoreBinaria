package model.estrutura.lista;
import java.lang.Exception;
import model.estrutura.lista.No;

public class ListaEncadeadaSimples<T>{
	private No<T> inicio = null;
	
	public void append(T elemento) {
		No<T> buffer = new No<>(elemento);
		if (this.inicio == null) {
			this.inicio = buffer;
		}else {
			this.last().setProximo(buffer);
		}
	}
	
	public No<T> get(int index) throws IllegalArgumentException{
		int i = 0;
		if(this.inicio == null) throw new IllegalArgumentException("Não existe item na lista.");
		No<T> buffer = this.inicio;
		for(i = 0; i< index; i++) {
			if(buffer.getProximo() == null) break;
			buffer = buffer.getProximo();
		}
		if (i < index) throw new IllegalArgumentException("O índice formado não existe.");
		return buffer;	
	}
	
	public int index(T elemento)throws IllegalArgumentException {
		if(this.inicio == null)throw new IllegalArgumentException("Não existe item na lista."); 
		int index = 0;
		if(this.inicio.getValor() == elemento) return index;
		No<T> buffer = this.inicio;
		do {
			if(buffer.getValor() == elemento) return index;
			buffer = buffer.getProximo();
			index++;
		}while(buffer != null);
			throw new IllegalArgumentException("Item não encontrado.");
	}
	
	public void insert(int index, T elemento)throws IllegalArgumentException{
		if(index == 0) {
			No<T> bufferNovo = new No<>(elemento);
			if(this.inicio != null) {
				No<T> bufferInicio = this.inicio;
				bufferNovo.setProximo(bufferInicio);
				this.inicio = bufferNovo;
			}else {
				this.inicio = bufferNovo;
			}
		}else {
			this.insert(this.get(--index), elemento);
		}
	}
	public void insert (No<T> item, T elemento)throws IllegalArgumentException{
		No<T> bufferNovo = new No<>(elemento);
		No<T> bufferProximo = item.getProximo();
		item.setProximo(bufferNovo);
		bufferNovo.setProximo(bufferProximo);
	}
	
	public No<T> last() throws IllegalArgumentException{
		if(this.inicio == null) throw new IllegalArgumentException("Não existe item na lista.");
		No<T> buffer = this.inicio;
		while(buffer.getProximo() != null) buffer = buffer.getProximo();
			return buffer;
	}
	
	public void remove(int index) {
		if(index == 0) {
			this.inicio.setValor(null);
			if(this.inicio.getProximo() == null) {
				this.inicio = null;
			} else {
				No<T> buffer = this.inicio.getProximo();
				this.inicio.setProximo(null);
				this.inicio = buffer;
			}
			return;
		}
		No<T> bufferAnterior = this.get(--index);
		No<T> item = bufferAnterior.getProximo();
		No<T> bufferProximo = item.getProximo();
		bufferAnterior.setProximo(bufferProximo);
		item.setProximo(null);
		item.setValor(null);
	}
	
	public int total() {
		if (this.inicio == null) return 0;
		No<T> buffer = this.inicio;
		int totalElementos = 0;
		do {
			totalElementos++;
			buffer = buffer.getProximo();
		}while(buffer != null);
		return totalElementos;
	}
	
	@Override
	public String toString() {
		if(this.inicio == null) return "[]";
		StringBuilder builder = new StringBuilder("[");
		No<T> buffer = this.inicio;
		builder.append(buffer.getValor());
		while(buffer.getProximo() != null) {
			builder.append(", ");
			buffer = buffer.getProximo();
			builder.append(buffer.getValor());
		}
		builder.append("]");
		return builder.toString();
	}
}
