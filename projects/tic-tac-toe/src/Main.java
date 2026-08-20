import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==================== Benvindo ao Jogo da velha  ====================");
        System.out.print("Informe [O] para jogar Online, [F] para jogar Offline ou [S] para Sair: ");
        char opc = ' ';
        do {
            opc = scanner.next().toUpperCase().charAt(0);
        } while (opc != 'O' && opc != 'F' && opc != 'S');
        if (opc == 'F') {
            jogaOffline();
        } else if (opc == 'O') {
            while (jogaOnline()){};
        } else System.exit(0);
    }

    public static boolean jogaOnline() {
        Jogador player1 = new Jogador();
        Jogador player2 = new Jogador();
        Jogador playerVencedor;
        Jogo game = new Jogo();
        String host = new String();
        String msg = new String();
        Scanner scanner = new Scanner(System.in);
        boolean minhaVez = false;
        System.out.println("************* Jogo da velha ONLINE *************");
        char opc = ' ';
        do {
            System.out.print("Informe [A] para Aguardar a conexão do outro jogador ou [C] para Conectar a um outro usuário. [S] para Sair: ");
            opc = scanner.next().toUpperCase().charAt(0);
        } while (opc != 'S' && opc != 'A' && opc != 'C');

        if (opc == 'C') {
            game.terminaPartida(!player1.identificaJogador(' '));
            do {
                System.out.print("Informe o hostname ou IP da máquina destino: ");
                host = scanner.next();
            } while (host == "");
            UDPComm commOut = new UDPComm(host, 1010);
            msg = player1.meuTime + player1.meuNome;
            commOut.setMsg(commOut.charToByte(msg.toCharArray()));
            commOut.sendMsg();
            //System.out.println(commOut.getMsgStr()+ " => "+commOut.host+ ":"+ commOut.port);
            UDPComm commIn = new UDPComm(1010);
            System.out.println("Aguardando informações do outro jogador...");
            if (!commIn.receiveMsg()) System.exit(0);
            msg = commIn.getMsgStr();
            player2.meuTime = msg.charAt(0);
            player2.meuNome = msg.substring(1);
            System.out.println(player2.meuNome + " joga remoto com o time " + player2.meuTime);
            minhaVez = true;

        } else if (opc == 'A') {
            UDPComm commIn = new UDPComm(1010);
            System.out.println("Aguardando conexão do outro jogador...");
            if (!commIn.receiveMsg()) System.exit(0);
            msg = commIn.getMsgStr();
            //System.out.println("Recebido: "+commIn.getMsgStr());
            player2.meuTime = msg.charAt(0);
            player2.meuNome = msg.substring(1);
            System.out.println("Jogador remoto a partir do endereço " + commIn.host);
            System.out.println(player2.meuNome + " joga remoto com o time " + player2.meuTime);
            System.out.println("Você jogará com o time " + Auxiliar.alternaTime(player2.meuTime));
            game.terminaPartida(!player1.identificaJogador(player2.meuTime));
            host = commIn.host;
            UDPComm commOut = new UDPComm(host, 1010);
            msg = player1.meuTime + player1.meuNome;
            commOut.setMsg(commOut.charToByte(msg.toCharArray()));
            //System.out.println("Enviando msg "+String.valueOf(commOut.msg)+ " para "+ commOut.host);
            commOut.sendMsg();
            minhaVez = false;

        } else System.exit(0);
        game.novoJogo();
        while (!game.ehFimDoJogo()) {
            game.atualizaTela();
            if (minhaVez) {
                game.terminaPartida(!player1.joga(game));
                UDPComm comm = new UDPComm(host, 1010);
                comm.setMsg(comm.charToByte(game.esteJogo));
                comm.sendMsg();
                minhaVez = false;
            } else {
                UDPComm comm = new UDPComm(host, 1010);
                System.out.println("Aguardando jogada remota do jogador " + player2.meuNome + " a partir do host " + host + "...");
                comm.receiveMsg();
                game.esteJogo = comm.getJogada();
                minhaVez = true;
            }
            if ((playerVencedor = game.temVencedor(player1, player2)) != null) {
                game.atualizaTela();
                playerVencedor.comemora();
                game.terminaPartida(true);
            } else if (game.ehEmpate()) {
                game.msgEmpate();
                game.terminaPartida(true);
            }
        }
        char novoJogo = ' ';
        do {
            System.out.print("Jogo online finalizado. Deseja iniciar novo jogo? [S]sim [N]ão: ");
            novoJogo = scanner.next().toUpperCase().charAt(0);
        } while (novoJogo != 'S' && novoJogo != 'N');
        return novoJogo == 'S';
    }

    public static void jogaOffline() {
        Jogador player1 = new Jogador();
        Jogador player2 = new Jogador();
        Jogador playerAtual;
        Jogador playerVencedor;
        Jogo game = new Jogo();
        System.out.println("************* Jogo da velha OFFLINE *************");
        game.novoJogo();
        game.terminaPartida(!player1.identificaJogador(' '));
        if (!game.ehFimDoJogo()) {
            game.terminaPartida(!player2.identificaJogador(player1.meuTime));
        }
        do {
            playerAtual = player1;
            while (!game.ehFimDoJogo()) {
                game.atualizaTela();
                game.terminaPartida(!playerAtual.joga(game));
                playerAtual = Auxiliar.alternaJogador(playerAtual, player1, player2);
                if ((playerVencedor = game.temVencedor(player1, player2)) != null) {
                    game.atualizaTela();
                    playerVencedor.comemora();
                    game.terminaPartida(true);
                } else if (game.ehEmpate()) {
                    game.msgEmpate();
                    game.terminaPartida(true);
                }
            }
            if (Auxiliar.maisUmaPartida()) {
                game.novoJogo();
            }
        } while (!game.ehFimDoJogo());
        Auxiliar.placar(player1, player2);
    }
}