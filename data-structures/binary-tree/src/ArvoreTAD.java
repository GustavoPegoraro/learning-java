import java.util.ArrayList;
import java.util.NoSuchElementException;
public class ArvoreTAD {
    private Nodo raiz;
    public ArvoreTAD() {
    }
    public boolean estaVazia() {
        return raiz == null;
    }
    public int tamanho() {
        return tamanhoRec(raiz);
    }
    private int tamanhoRec(Nodo n) {
        if (n == null)
            return 0;
        return 1 + tamanhoRec(n.esq) + tamanhoRec(n.dir);
    }
    public void limpa() {
        raiz = null;
    }
    public void insere(int elem) {
        if (estaVazia()) {
            raiz = new Nodo(elem);
        }
        else {
            insereRec(elem, raiz);
        }
    }
    private void insereRec(int elem, Nodo n) {
         if (elem == n.elem) {
             System.out.println("Ja existe o elemento associado!");
         }
         else {
             if (elem > n.elem) {
                 if (n.dir == null) {
                     n.dir = new Nodo(elem);
                 }
                 else {
                     insereRec(elem, n.dir);
                 }
             }
             else {
                 if (n.esq == null) {
                     n.esq = new Nodo(elem);
                 }
                 else {
                     insereRec(elem, n.esq);
                 }
             }
         }
    }
    public boolean pesquisa(int elem) {
        return pesquisaRec(elem, raiz);
    }
    private boolean pesquisaRec(int elem, Nodo n) {
        if (n==null) {
            return false;
        }
        else if (elem == n.elem) {
            return true;
        }
        else {
            if (elem<n.elem) {
                return pesquisaRec(elem, n.esq);
            }
            else {
                return pesquisaRec(elem, n.dir);
            }
        }
    }
    public void imprimeEmOrdem() {
        imprimeEmOrdemRec(raiz);
    }
    private void imprimeEmOrdemRec(Nodo n) {
        if (n != null) {
            imprimeEmOrdemRec(n.esq);
            System.out.print(n.elem + " ");
            imprimeEmOrdemRec(n.dir);
        }
    }
    public void imprimePosOrdem() {
        imprimePosOrdemRec(raiz);
    }
    private void imprimePosOrdemRec(Nodo n) {
        if (n != null) {
            imprimePosOrdemRec(n.esq);
            imprimePosOrdemRec(n.dir);
            System.out.print(n.elem + " ");
        }
    }
    public void imprimePreOrdem() {
        imprimePreOrdemRec(raiz);
    }
    private void imprimePreOrdemRec(Nodo n) {
        if (n != null) {
            System.out.print(n.elem + " ");
            imprimePreOrdemRec(n.esq);
            imprimePreOrdemRec(n.dir);
        }
    }
    public int acessaMaior() {
        if (raiz == null)
            throw new NoSuchElementException("Árvore vazia");
        return acessaMaiorRec(raiz);
    }
    private int acessaMaiorRec(Nodo n) {
        if (n.dir == null)
            return n.elem;
        return acessaMaiorRec(n.dir);
    }
    public int acessaMenor() {
        if (raiz == null)
            throw new NoSuchElementException("Árvore vazia");
        return acessaMenorRec(raiz);
    }
    private int acessaMenorRec(Nodo n) {
        if (n.esq == null)
            return n.elem;
        return acessaMenorRec(n.esq);
    }
    public ArrayList<Integer> criaVetorEmOrdem() {
        ArrayList<Integer> vetor = new ArrayList<>();
        criaVetorEmOrdem(raiz, vetor);
        return vetor;
    }
    private void criaVetorEmOrdem(Nodo n, ArrayList<Integer> vetor) {
        if (n != null) {
            criaVetorEmOrdem(n.esq, vetor);
            vetor.add(n.elem);
            criaVetorEmOrdem(n.dir, vetor);
        }
    }
    public void balanceamentoEstatico() {
        ArrayList<Integer> vetorEmOrdem = criaVetorEmOrdem();
        limpa();
        preencheArvoreBinaria(vetorEmOrdem, 0, vetorEmOrdem.size() - 1);
    }
    private void preencheArvoreBinaria(ArrayList<Integer> vetorEmOrdem, int inicio, int fim) {
        if (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            insere(vetorEmOrdem.get(meio));
            preencheArvoreBinaria(vetorEmOrdem, inicio, meio - 1);
            preencheArvoreBinaria(vetorEmOrdem, meio + 1, fim);
        }
    }
    public void imprimeArvore() {
        this.imprimeArvoreRec(this.raiz, 0);
    }
    private void imprimeArvoreRec(Nodo n, int depth) {
        if (n == null) {
            return;
        }
        this.imprimeArvoreRec(n.dir, depth + 1);
        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
        System.out.println(n.elem);
        this.imprimeArvoreRec(n.esq, depth + 1);
    }
    public void remove(int elem) {
        this.raiz = this.removeRec(elem, this.raiz);
    }
    private Nodo removeRec(int elem, Nodo n) {
        if (n == null) {
            return null;
        } else if (elem == n.elem) {
            if (n.esq == null && n.dir == null) {
                return null;
            } else if (n.esq == null) {
                return n.dir;
            } else if (n.dir == null) {
                return n.esq;
            } else {
                Nodo aux = n.dir;
                while (aux.esq != null) {
                    aux = aux.esq;
                }
                n.elem = aux.elem;
                n.dir = this.removeRec(aux.elem, n.dir);
            }
        } else if (elem < n.elem) {
            n.esq = removeRec(elem, n.esq);
        } else {
            n.dir = removeRec(elem, n.dir);
        }
        return n;
    }
}