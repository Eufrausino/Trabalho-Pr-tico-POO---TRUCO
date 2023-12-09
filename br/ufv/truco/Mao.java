package br.ufv.truco;
import java.util.ArrayList;

public class Mao {
    private int valor = 2;
    private int numJogadores;
    private ArrayList<Rodada> rodadas = new ArrayList<>();
    private Jogador[] jogadores;

    public Mao(Equipe eq1, Equipe eq2) {
        if(eq1.ehDupla() && eq2.ehDupla()) {
            // As equipes são duplas
            numJogadores = 4;
            jogadores = new Jogador[4];
            jogadores[0] = eq1.getJogador1();
            jogadores[1] = eq2.getJogador1();
            jogadores[2] = eq1.getJogador2();
            jogadores[3] = eq2.getJogador2();
            return;
        }
        // As "equipes" são jogadores individuais
        numJogadores = 2;
        jogadores = new Jogador[2];
        jogadores[0] = eq1.getJogador1();
        jogadores[1] = eq2.getJogador1();
    }

    public ArrayList<Rodada> getRodadas() {
        return rodadas;
    }

    public int getValor() {
        return valor;
    }

    // Determina a quantidade de pontos a partir de um "nível" de truco
    private int calculaValor(int nivelTruco) {
        if(nivelTruco <= 1) return 2 + nivelTruco * 2;
        else return 4 + nivelTruco * 2;
    }

    // Distribui cartas aleatorias a todos os jogadores
    private void distribuiCartas(Baralho baralho) {
        // Embaralha o baralho e descarta quaisquer cartas que os jogadores já tenham
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
    public int executaMao(int posInicial, Baralho baralho) {
        int nivelTruco = 0, numRodada = 0;
        int vitorias1 = 0, vitorias2 = 0, empates = 0;
        distribuiCartas(baralho);


        int i = posInicial;
        while (numRodada < 3 && vitorias1 < 2 && vitorias2 < 2) {
            Rodada rodada = new Rodada(nivelTruco, numJogadores, jogadores);
            System.out.println("\n=== RODADA " + ++numRodada + " ===\n");
            // O jogador que inicia a rodada é o último a ter ganhado
            i = rodada.executaRodada(i);
            this.rodadas.add(rodada);

            // Determina-se quantos pontos a mão vale a partir de cada rodada
            if(nivelTruco < rodada.getNivelTruco()) {
                nivelTruco = rodada.getNivelTruco();
                valor = calculaValor(nivelTruco);
            }

            // Pode ser que a rodada tenha decidido o desfecho da mão (truco)
            if(rodada.decideMao()) {
                // Índices pares são vitórias da equipe 1 e os ímpares, da 2
                if(i % 2 == 0) return 1;
                else return 2;
            }
            // Índices negativos denotam empates, os pares denotam uma
            // vitória da equipe 1 e os ímpares, uma da equipe 2
            if(i < 0) {
                i = -1 * (i + 1); // próximo jogador é o responsável pelo empate
                ++empates;
            } else if(i % 2 == 0) {
                ++vitorias1;
            } else {
                ++vitorias2;
            }
        }
        // NOTA os empates devem ser levados em consideração
        return vitorias1 > vitorias2 ? 1 : 2;
    }
}
