public class VetorTAD {
    private int[] vetor;
    private int numElementos;
    public VetorTAD(int max) {
        vetor = new int[max];
        numElementos = 0;
    }
    public int tamanho() {
        return numElementos;
    }
    public boolean estaVazia() {
        return numElementos == 0;
    }
    public boolean estaCheia() {
        return numElementos == vetor.length;
    }
    public void imprime() {
        for (int i = 0; i < numElementos; i++) {
            System.out.println(vetor[i]);
        }
        System.out.println("\n");
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < numElementos; i++) {
            sb.append(vetor[i]);
            if (i < numElementos - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    public void insereFinal(int valor) {
        if (estaCheia()) {
            System.out.println("Vetor cheio, não é possível inserir mais elementos.");
            return;
        }
        vetor[numElementos] = valor;
        numElementos++;
    }
    public void insereInicio(int valor) {
        if (estaCheia()) {
            System.out.println("Vetor cheio, não é possível inserir mais elementos.");
            return;
        }
        for (int i = numElementos; i > 0; i--) {
            vetor[i] = vetor[i - 1];
        }
        vetor[0] = valor;
        numElementos++;
    }
    public void insere(int pos, int valor) {
        if (estaCheia()) {
            System.out.println("Vetor cheio, não é possível inserir mais elementos.");
            return;
        }
        if (pos < 0 || pos > numElementos) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }
        for (int i = numElementos; i > pos; i--) {
            vetor[i] = vetor[i - 1];
        }
        vetor[pos] = valor;
        numElementos++;
    }
    public void removeFinal() {
        if (estaVazia()) {
            System.out.println("Vetor vazio, não é possível remover elementos.");
            return;
        }
        numElementos--;
    }
    public void removeInicio() {
        if (estaVazia()) {
            System.out.println("Vetor vazio, não é possível remover elementos.");
            return;
        }
        for (int i = 0; i < numElementos - 1; i++) {
            vetor[i] = vetor[i + 1];
        }
        numElementos--;
    }
    public void remove(int pos) {
        if (estaVazia()) {
            System.out.println("Vetor vazio, não é possível remover elementos.");
            return;
        }
        if (pos < 0 || pos >= numElementos) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }
        for (int i = pos; i < numElementos - 1; i++) {
            vetor[i] = vetor[i + 1];
        }
        numElementos--;
    }
    public boolean pesquisa(int valor) {
        for (int i = 0; i < numElementos; i++) {
            if (vetor[i] == valor) {
                return true;
            }
        }
        return false;
    }
    public void altera(int pos, int valor) {
        if (pos < 0 || pos >= numElementos) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }
        vetor[pos] = valor;
    }
    public void limpa() {
        numElementos = 0;
    }
}