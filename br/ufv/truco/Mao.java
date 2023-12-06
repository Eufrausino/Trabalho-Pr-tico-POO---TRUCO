package br.ufv.truco;
import java.util.ArrayList;

public class Mao {
    private int valor = 2;
    private int qtdJogadores;
    private ArrayList<Rodada> rodadas = new ArrayList<>();
    private ArrayList<Jogador> jogadores = new ArrayList<>();

    public Mao(Equipe eq1, Equipe eq2) {
        qtdJogadores = 2;
        // Adiciona os dois jogadores que certamente estarão nas equipes
        jogadores.add(eq1.getJogador1());
        jogadores.add(eq2.getJogador1());
        // Adiciona os demais jogadores se existirem; 1x1 ou 2x2?
        if(eq1.ehDupla() && eq2.ehDupla()) {
            qtdJogadores = 4;
            jogadores.add(eq1.getJogador2());
            jogadores.add(eq2.getJogador2());
        }
    }

    public ArrayList<Rodada> getRodadas() {
        return rodadas;
    }

    public int getValor() {
        return valor;
    }

    // Distribui cartas aleatorias a todos os jogadores
    private void distribuiCartas(Baralho baralho) {
        // Embaralha o baralho e descarta quaisquer cartas que os jogadore já tenham
        baralho.reiniciar();
        for(Jogador j : jogadores) j.largaCartas();

        // Distribui as cartas
        for (int i = 0; i < 3; i++) {
            for (Jogador jogador : jogadores) {
                // Verifica se ainda há cartas no baralho
                if (baralho.getCartasRestantes() > 0) {
                    // Remove a carta do topo do baralho e a dá ao jogador
                    Carta carta = baralho.retiraCartaAleatoria();
                    jogador.recebeCarta(carta);
                }
            }
        }
    }

    // Executa uma mão, retornando o índice da equipe vencedora; ou seja,
    // 1 para a equipe 1 e 2 para a equipe 2
    public int executaMao(Baralho baralho) {
        int vitorias1 = 0, vitorias2 = 0;
        distribuiCartas(baralho);

        int i = 0;
        while (rodadas.size() < 3 || vitorias1 == 2 || vitorias2 == 2) {
            Rodada rodada = new Rodada();
            // O jogador que inicia a rodada é o último a ter ganhado
            i = rodada.executaRodada(jogadores, qtdJogadores, i);
            this.rodadas.add(rodada);

            // Um índice de jogador par denota uma vitória da equipe 1;
            // um índice ímpar denota uma vitória da equipe 2
            if(i % 2 == 0) ++vitorias1;
            else ++vitorias2;
        }
        return vitorias1 > vitorias2 ? 1 : 2;
    }
}
