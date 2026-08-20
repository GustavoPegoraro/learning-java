import javax.swing.*;
import java.util.Scanner;

public class Jogador implements InterfaceJogador{
    char meuTime;
    String meuNome;
    int vitorias = 0;

    private boolean leTime(){
        Scanner entrada = new Scanner(System.in);
        char timeLido;
        do {
            System.out.print("Escolha seu time ["+Auxiliar.TIME01+"] ["+Auxiliar.TIME02+"] ou [S] para sair: ");
            timeLido = entrada.next().toUpperCase().charAt(0);
        } while (timeLido != Auxiliar.TIME01 && timeLido != Auxiliar.TIME02 && timeLido != 'S');
        if (timeLido != 'S'){
            meuTime = timeLido;
            return true;
        } else return false;
    }

    private String leNome(){
        Scanner entrada = new Scanner(System.in);
        String nome = "";
        do {
            System.out.print("Nome do jogador do time "+this.meuTime+": ");
            nome = entrada.next();
        } while (nome == "");
        return nome;
    }

    public boolean identificaJogador(char outroTime){
        if (outroTime != ' '){
            this.meuTime = Auxiliar.alternaTime(outroTime);
            this.meuNome = leNome();
            return true;
        }else {
            if (leTime()) {
            this.meuNome = leNome();
            return true;
            } else return false;
        }
    }

    public boolean joga(Jogo game){
        int posicao;
        boolean jogadaValida = false;
        Scanner entrada = new Scanner(System.in);
        do{
            System.out.println(this.meuNome+ " jogando com " + this.meuTime);
            System.out.print("Informe a posição de jogo. 0 para encerrar partida: ");
            posicao = entrada.nextInt();
            if ((posicao >= 1) && (posicao <=9)) {
                if (game.esteJogo[posicao - 1] == ' ') {
                    game.esteJogo[posicao-1] = this.meuTime;
                    jogadaValida = true;
                }
            }
        } while (!jogadaValida && !(posicao==0));
        if (posicao==0) return false;
        else return true;
    }

    public void comemora(){
        this.vitorias++;
        System.out.println();
        System.out.println("Uma grandiosa vitória de  " + this.meuNome);
        System.out.println();
        System.out.println("Até o momento o jogador "+this.meuNome+" possui  "+this.vitorias +" vitórias");

    }
    @Override
    public String toString() {
        if (meuNome != null)
            return "Jogador: " + meuNome + " time [" + meuTime +"]" + " Vitórias: "+ vitorias;
        else return "Jogador não identificado.";
    }
}
