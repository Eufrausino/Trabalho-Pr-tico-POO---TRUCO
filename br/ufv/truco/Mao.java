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

    // Determina a quantidade de pontos a partir de um "nível" de truco
    private int calculaValor(int trucado) {
        if(trucado <= 1) return 2 + trucado * 2;
        else return 4 + trucado * 2;
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
        int trucado = 0;
        int vitorias1 = 0, vitorias2 = 0, empates = 0;
        distribuiCartas(baralho);
        
        int conta_rodada = 0;

        int i = posInicial;
        while (rodadas.size() < 3 || vitorias1 == 2 || vitorias2 == 2) {
            Rodada rodada = new Rodada(trucado);
            System.out.println("Rodada: " + ++conta_rodada);
            // O jogador que inicia a rodada é o último a ter ganhado
            i = rodada.executaRodada(jogadores, qtdJogadores, i);
            this.rodadas.add(rodada);
            // Determina-se quantos pontos a mão vale a partir de cada rodada
            if(trucado < rodada.getTrucado()) {
                trucado = rodada.getTrucado();
                valor = calculaValor(trucado);
            }

            // Pode ser que a rodada tenha decidido o desfecho da mão (truco)
            if(rodada.terminaMao()) {
                // Índices pares são vitórias da equipe 1 e os ímpares, da 2
                if(i % 2 == 0) return 1;
                else return 2;
            }
            // Índices negativos denotam empates, os pares denotam uma
            // vitória da equipe 1 e os ímpares, uma da equipe 2
            if(i < 0) {
                ++empates;
                i = 0;
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