import java.lang.reflect.Array;

public class Main {
    public static void main(String[] args) {
        VetorTAD vetor = new VetorTAD(5);

        vetor.insereFinal(4);
        vetor.insereFinal(5);
        vetor.imprime();

        vetor.insereInicio(1);
        vetor.imprime();

        vetor.insere(1,2);
        vetor.insere(2,3);
        vetor.imprime();

        vetor.remove(2);
        vetor.removeInicio();
        vetor.removeFinal();
        vetor.imprime();

        vetor.altera(0,1);
        vetor.altera(1,2);
        vetor.imprime();

        System.out.println(vetor.pesquisa(1));
        System.out.println(vetor.pesquisa(3));

        vetor.limpa();
        vetor.imprime();
    }
}