package listaCircular;

public class ListaCircular<T> {

	private No<T> cabeca;
	private No<T> cauda;
	private int tamanhoLista;

	public ListaCircular() {

		this.cabeca = null;
		this.cauda = null;
		this.tamanhoLista = 0;
	}

	public T get(int index) {
		return this.getNo(index).getConteudo();
	}

	// Método para adicionar um elemento à lista
	public void add(T conteudo) {
		// Cria um novo nó com o conteúdo fornecido
		No<T> novoNo = new No<>(conteudo);
		// Se a lista estiver vazia, define o novo nó como cabeça e cauda da lista
		if (this.tamanhoLista == 0) {
			this.cabeca = novoNo;
			this.cauda = this.cabeca;
			this.cabeca.setNoProximo(cauda);
		} else {
			// Caso contrário, adiciona o novo nó ao final da lista
			novoNo.setNoProximo(this.cauda);
			this.cabeca.setNoProximo(novoNo);
			this.cauda = novoNo;
		}
		// Aumenta o tamanho da lista
		this.tamanhoLista++;
	}

	// Método para remover um elemento da lista em um determinado índice
	public void remove(int index) {
		// Verifica se o índice é maior ou igual ao tamanho da lista e lança uma exceção
		if (index >= this.tamanhoLista) {
			throw new IndexOutOfBoundsException("O indice é maior que o tamanho da lista");
		}

		// Cria um nó auxiliar para percorrer a lista
		No<T> noAuxiliar = this.cauda;
		// Se o índice for 0, remove a cauda
		if (index == 0) {
			this.cauda = this.cauda.getNoProximo();
			this.cabeca.setNoProximo(this.cauda);
		} else if (index == 1) {
			// Se o índice for 1, remove o segundo elemento da lista
			this.cauda.setNoProximo(this.cauda.getNoProximo().getNoProximo());
		} else {
			// Caso contrário, percorre a lista até o índice desejado
			for (int i = 0; i < index - 1; i++) {
				noAuxiliar = noAuxiliar.getNoProximo();
			}
			// Remove o elemento desejado
			noAuxiliar.setNoProximo(noAuxiliar.getNoProximo().getNoProximo());
		}
		// Diminui o tamanho da lista
		this.tamanhoLista--;
	}

	// Método para obter um nó em um determinado índice
	public No<T> getNo(int index) {
		// Verifica se a lista está vazia e lança uma exceção
		if (this.isEmpty()) {
			throw new IndexOutOfBoundsException("A lista está vazia");
		}

		// Se o índice for 0, retorna a cauda
		if (index == 0) {
			return this.cauda;
		}

		// Cria um nó auxiliar para percorrer a lista
		No<T> noAuxiliar = this.cauda;
		// Percorre a lista até o índice desejado
		for (int i = 0; (i < index) && (noAuxiliar != null); i++) {
			noAuxiliar = noAuxiliar.getNoProximo();
		}
		// Retorna o nó encontrado
		return noAuxiliar;
	}

	// Método para verificar se a lista está vazia
	public boolean isEmpty() {
		// Retorna verdadeiro se o tamanho da lista for 0, caso contrário retorna falso
		return this.tamanhoLista == 0 ? true : false;
	}

	// Método para obter o tamanho da lista
	public int size() {
		// Retorna o tamanho da lista
		return this.tamanhoLista;
	}

	@Override
	public String toString() {
		String strRetorno = "";

		No<T> noAuxiliar = this.cauda;
		for (int i = 0; i < this.size(); i++) {
			strRetorno += "[No{conteúdo = " + noAuxiliar + "}]--->";
			noAuxiliar = noAuxiliar.getNoProximo();

		}
		strRetorno += this.size() != 0 ? "(Retorna ao inicio)" : "[]";

		return strRetorno;
	}

}
