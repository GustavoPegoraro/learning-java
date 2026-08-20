public class ListaTAD {
    int numElementos = 0;
    Nodo inicio = null;
    Nodo fim = null;
    public int tamanho() {
        return this.numElementos;
    }
    public boolean estaVazia() {
        return this.numElementos == 0;
    }
    public void imprime() {
        for(Nodo aux = this.inicio; aux != null; aux = aux.prox) {
            System.out.print(aux.elem + " ");
        }
        System.out.printf("\n");
    }
    public void imprimeReverso() {
        for(Nodo atual = this.fim; atual != null; atual = atual.ant) {
            System.out.print(atual.elem + " ");
        }
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(Nodo atual = this.inicio; atual != null; atual = atual.prox) {
            sb.append(atual.elem).append(" ");
        }
        return sb.toString();
    }
    public int acessa(int pos) {
        if (pos >= 0 && pos < this.numElementos) {
            Nodo atual = this.inicio;

            for(int i = 0; i < pos; ++i) {
                atual = atual.prox;
            }
            return atual.elem;
        } else {
            throw new IndexOutOfBoundsException("Posição inválida");
        }
    }
    public void insereFinal(int elem) {
        Nodo novo = new Nodo();
        novo.elem = elem;
        novo.prox = null;
        if (this.estaVazia()) {
            novo.ant = null;
            this.inicio = novo;
        } else {
            novo.ant = this.fim;
            this.fim.prox = novo;
        }
        this.fim = novo;
        ++this.numElementos;
    }
    public void insereInicio(int elem) {
        Nodo novo = new Nodo();
        novo.elem = elem;
        if (this.estaVazia()) {
            this.fim = novo;
        } else {
            this.inicio.ant = novo;
            novo.prox = this.inicio;
        }
        this.inicio = novo;
        ++this.numElementos;
    }
    public void insere(int pos, int elem) {
        if (pos >= 0 && pos <= this.numElementos) {
            if (pos == 0) {
                this.insereInicio(elem);
            } else if (pos == this.numElementos) {
                this.insereFinal(elem);
            } else {
                Nodo atual = this.inicio;

                for(int i = 0; i < pos - 1; ++i) {
                    atual = atual.prox;
                }

                Nodo novo = new Nodo();
                novo.elem = elem;
                novo.prox = atual.prox;
                novo.ant = atual;
                atual.prox.ant = novo;
                atual.prox = novo;
                ++this.numElementos;
            }
        } else {
            throw new IndexOutOfBoundsException("Posição inválida");
        }
    }
    public void removeFinal() {
        if (this.estaVazia()) {
            System.out.println("Lista vazia! Não há elementos.");
        } else if (this.numElementos == 1) {
            this.inicio = null;
            this.fim = null;
            this.numElementos = 0;
        } else {
            this.fim = this.fim.ant;
            this.fim.prox = null;
            --this.numElementos;
        }

    }
    public void removeInicio() {
        if (this.estaVazia()) {
            throw new IllegalStateException("Lista vazia");
        } else {
            if (this.inicio == this.fim) {
                this.inicio = null;
                this.fim = null;
            } else {
                this.inicio = this.inicio.prox;
                this.inicio.ant = null;
            }
            --this.numElementos;
        }
    }
    public void remove(int pos) {
        if (pos >= 0 && pos < this.numElementos) {
            if (pos == 0) {
                this.removeInicio();
            } else if (pos == this.numElementos - 1) {
                this.removeFinal();
            } else {
                Nodo atual = this.inicio;

                for(int i = 0; i < pos; ++i) {
                    atual = atual.prox;
                }

                atual.ant.prox = atual.prox;
                atual.prox.ant = atual.ant;
                --this.numElementos;
            }
        } else {
            throw new IndexOutOfBoundsException("Posição inválida");
        }
    }
    public boolean pesquisa(int valor) {
        for(Nodo atual = this.inicio; atual != null; atual = atual.prox) {
            if (atual.elem == valor) {
                return true;
            }
        }
        return false;
    }
    public void altera(int pos, int valor) {
        if (pos >= 0 && pos < this.numElementos) {
            Nodo atual = this.inicio;

            for(int i = 0; i < pos; ++i) {
                atual = atual.prox;
            }
            atual.elem = valor;
        } else {
            throw new IndexOutOfBoundsException("Posição inválida");
        }
    }
    public void limpa() {
        this.inicio = null;
        this.fim = null;
        this.numElementos = 0;
    }
}