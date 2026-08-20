import java.util.Scanner;

public class Auxiliar {

    final static char TIME01 = 'X';
    final static char TIME02 = 'O';

    public static Jogador alternaJogador(Jogador jatual, Jogador j1, Jogador j2){
        if (jatual == j1) return j2;
        else return j1;
    }

    public static char alternaTime(char timeAtual){
        if (timeAtual == Auxiliar.TIME01)
            return Auxiliar.TIME02;
        else return Auxiliar.TIME01;
    }

    public static boolean maisUmaPartida(){
        Scanner entrada = new Scanner(System.in);
        String opc = new String();
        do {
            System.out.print("Deseja jogar mais uma partida [S]im ou [N]ão]: ");
            opc = entrada.next();
        } while (opc == "");
        return (opc.toUpperCase().charAt(0) == 'S');
    }

    public static void placar(Jogador j1, Jogador j2){
        System.out.println("\n**********************  Placar da Partida **********************");
        System.out.println(j1);
        System.out.println(j2);
    }
}
