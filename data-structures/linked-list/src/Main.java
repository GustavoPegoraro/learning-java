public class Main {
    public static void main(String[] args) {
        ListaTAD lista = new ListaTAD();
        lista.insereInicio(1);
        lista.insereFinal(2);
        lista.insereFinal(3);
        lista.imprime();

        lista.insereInicio(0);
        lista.insere(1, 2);
        lista.imprime();


        lista.altera(1, 7);
        lista.imprime();
        System.out.println("\n"+lista.pesquisa(7)+"\n");

        lista.remove(1);
        lista.imprime();
        System.out.println("\n"+lista.pesquisa(7)+"\n");

        lista.imprimeReverso();

        System.out.println("\n"+lista.tamanho()+"\n");

        lista.limpa();
        System.out.println("\n"+lista.tamanho()+"\n");
    }
}