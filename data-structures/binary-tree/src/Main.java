import java.util.Scanner;
public class Main {
    private static ArvoreTAD arvore = new ArvoreTAD();
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("1. Inserir elemento\n" +
                                "2. Remover elemento\n" +
                                "3. Verificar se está vazia\n" +
                                "4. Calcular tamanho\n" +
                                "5. Imprimir árvore\n" +
                                "6. Imprimir em ordem\n" +
                                "7. Imprimir pré-ordem\n" +
                                "8. Imprimir pós-ordem\n" +
                                "9. Acessar o menor elemento\n" +
                                "10. Acessar o maior elemento\n" +
                                "11. Pesquisar elemento\n" +
                                "12. Limpar árvore\n" +
                                "13. Balanceamento estático\n" +
                                "0. Sair\n" +
                                "Escolha uma opção: ");
            opcao = input.nextInt();
            switch (opcao) {
                case 1:
                    System.out.print("Digite o elemento a ser inserido: ");
                    int elemento = input.nextInt();
                    arvore.insere(elemento);
                    break;
                case 2:
                    System.out.print("Digite o elemento a ser removido: ");
                    int elementoRemover = input.nextInt();
                    arvore.remove(elementoRemover);
                    break;
                case 3:
                    System.out.println("A árvore está vazia? " + arvore.estaVazia());
                    break;
                case 4:
                    System.out.println("Tamanho da árvore: " + arvore.tamanho());
                    break;
                case 5:
                    System.out.println("Imprimindo árvore:");
                    arvore.imprimeArvore();
                    break;
                case 6:
                    System.out.print("Imprimindo em ordem: ");
                    arvore.imprimeEmOrdem();
                    System.out.println();
                    break;
                case 7:
                    System.out.print("Imprimindo pré-ordem: ");
                    arvore.imprimePreOrdem();
                    System.out.println();
                    break;
                case 8:
                    System.out.print("Imprimindo pós-ordem: ");
                    arvore.imprimePosOrdem();
                    System.out.println();
                    break;
                case 9:
                    System.out.println("Menor elemento: " + arvore.acessaMenor());
                    break;
                case 10:
                    System.out.println("Maior elemento: " + arvore.acessaMaior());
                    break;
                case 11:
                    System.out.print("Digite o elemento a ser pesquisado: ");
                    int elementoPesquisar = input.nextInt();
                    System.out.println("Elemento encontrado? " + arvore.pesquisa(elementoPesquisar));
                    break;
                case 12:
                    arvore.limpa();
                    System.out.println("Árvore limpa.");
                    break;
                case 13:
                    arvore.balanceamentoEstatico();
                    System.out.println("Árvore balanceada.");
                    break;
                case 0:
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        while (opcao != 0);
        input.close();
    }
}