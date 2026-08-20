public class Jogo implements InterfaceJogo{
    char[] esteJogo = new char[9];
    boolean acabouJogo;

    public boolean ehFimDoJogo(){
        return acabouJogo;
    }

    public void terminaPartida(boolean termina){
        acabouJogo = termina;
    }

    public void novoJogo(){
        int i;
        // zera o array de jogo
        for (i=0;i<=8;i++) esteJogo[i] = ' ';
        acabouJogo = false;
    }

    private char testaLinha(){
        if ((this.esteJogo[0] == this.esteJogo[1]) && ((this.esteJogo[1] == this.esteJogo[2])))
            return this.esteJogo[0];
        else if ((this.esteJogo[3] == this.esteJogo[4]) && ((this.esteJogo[4] == this.esteJogo[5])))
            return this.esteJogo[3];
        else if ((this.esteJogo[6] == this.esteJogo[7]) && ((this.esteJogo[7] == this.esteJogo[8])))
            return this.esteJogo[6];
        else return ' ';
    }

    private  char  testaColuna(){
        if ((this.esteJogo[0] == this.esteJogo[3]) && ((this.esteJogo[3] == this.esteJogo[6])))
            return this.esteJogo[0];
        else if ((this.esteJogo[1] == this.esteJogo[4]) && ((this.esteJogo[4] == this.esteJogo[7])))
            return this.esteJogo[1];
        else if ((this.esteJogo[2] == this.esteJogo[5]) && ((this.esteJogo[5] == this.esteJogo[8])))
            return this.esteJogo[2];
        else return ' ';
    }

    private  char testaDiagonal() {
        if ((this.esteJogo[0] == this.esteJogo[4]) && ((this.esteJogo[4] == this.esteJogo[8])))
            return this.esteJogo[0];
        else if ((this.esteJogo[6] == this.esteJogo[4]) && ((this.esteJogo[4] == this.esteJogo[2])))
            return this.esteJogo[6];
        else return ' ';
    }

    public Jogador temVencedor(Jogador j1, Jogador j2){
        char quemVenceu;
        quemVenceu = testaLinha();
        if (quemVenceu == ' ') {quemVenceu = testaColuna();}
        if (quemVenceu == ' ') {quemVenceu = testaDiagonal();}

        if (quemVenceu == j1.meuTime) return j1;
        else if (quemVenceu == j2.meuTime) return j2;
        else return null;
    }

    public void msgEmpate(){
        System.out.println("");
        System.out.println("O jogo acabou empatado!");
    }

    public boolean ehEmpate(){
        int i;
        boolean ehFim = true;
        for (i = 0; i <= 8; i++)
            if (this.esteJogo[i] == ' ') ehFim = false;
        return ehFim;
    }

    public void atualizaTela(){
        int i;
        for (i = 0; i <= 8; i++) {
            if (esteJogo[i] == ' ') {
                System.out.print("[" + (i+1) + "]");
            } else {
                System.out.print("[" + esteJogo[i] + "]");
            }
            if (i == 2 || i == 5 || i == 8) System.out.println();
        }
    }

}
