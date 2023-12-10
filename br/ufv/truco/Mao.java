package br.ufv.truco;
import java.util.ArrayList;

public class Mao {
    private int numJogadores;
    private int valor = calculaValor(0);
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
    protected int calculaValor(int nivelTruco) {
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
    // 1 para a equipe 1 e 2 para a equipe 2.
    public int executaMao(int posInicial, int ganhadorAnterior, Baralho baralho) {
        // Obrigado por não ter valores padrão Java! :)
        return executaMao(posInicial, ganhadorAnterior, false, true, baralho, false);
    }

    // Executa uma mão, retornando o índice da equipe vencedora; ou seja,
    // 1 para a equipe 1 e 2 para a equipe 2. Tem a opção de poder ou não
    // trucar (útil para a mão de dez)
    public int executaMao(int posInicial, int ganhadorAnterior, boolean naoPodeTrucar,
            boolean verCartas, Baralho baralho, boolean naoPodeEncobrir) {
        int nivelTruco = 0, numRodada = 0;
        distribuiCartas(baralho);

        // Quando há um empate, existem duas possibilidades. A primeira é que
        // houve alguma vitória anteriormente. Nesse caso, a primeira equipe a
        // conquistar a vitória ganha. A segunda é que a rodada a resultar em
        // um empate foi a primeira. Nesse caso, a próxima rodada ganha. A
        // variável houveEmpate denota a ocorrência anterior de um empate, e
        // primeiroGanhador contém o índice da primeira equipe a ganhar ou 0,
        // caso não tenham havido vitórias.
        int primeiroGanhador = 0;
        boolean houveEmpate = false;

        int i = posInicial % numJogadores;
        int vitorias1 = 0, vitorias2 = 0;
        while (numRodada < 3 && vitorias1 < 2 && vitorias2 < 2) {
            ++numRodada;
            Rodada rodada = new Rodada(nivelTruco, numJogadores, jogadores);
            System.out.printf("\n=== RODADA %d ===\n\n", numRodada);
            // O jogador que inicia a rodada é o último a ter ganhado
            i = rodada.executaRodada(i, naoPodeTrucar, verCartas, naoPodeEncobrir);
            this.rodadas.add(rodada);

            // Determina-se quantos pontos a mão vale a partir de cada rodada
            int rodadaNivel = rodada.getNivelTruco();
            if(nivelTruco < rodadaNivel) {
                nivelTruco = rodadaNivel;
                this.valor = calculaValor(nivelTruco);
            }

            // Pode ser que a rodada tenha decidido o desfecho da mão; isso
            // acontece caso alguém tenha corrido
            if(rodada.decideMao()) {
                // Índices pares são vitórias da equipe 1 e os ímpares, da 2
                // (uma rodada decisiva jamais será um empate)
                if(i % 2 == 0) return 1;
                else return 2;
            }

            // Índices negativos denotam empates, os pares denotam uma
            // vitória da equipe 1 e os ímpares, uma da equipe 2
            if(i < 0) {
                i = -1 * (i + 1); // próximo jogador é o responsável pelo empate
                houveEmpate = true;
                if(primeiroGanhador != 0)
                    // Houve pelo menos uma vitória anteriormente. A equipe
                    // que conquistou a primeira vitória ganha
                    return primeiroGanhador;
            } else if(i % 2 == 0) {
                ++vitorias1;
                if(primeiroGanhador == 0)
                    primeiroGanhador = 1;
                // Se a primeira rodada foi um empate, a equipe 1 ganhou
                if(houveEmpate) return 1;
            } else {
                ++vitorias2;
                if(primeiroGanhador == 0)
                    primeiroGanhador = 2;
                // Se a primeira rodada foi um empate, a equipe 2 ganhou
                if(houveEmpate) return 2;
            }
        }
        // Se TODAS as rodadas foram empates, ganha a equipe que ganhou a
        // última mão
        if(vitorias1 == 0 && vitorias2 == 0)
            return ganhadorAnterior;
        return vitorias1 > vitorias2 ? 1 : 2;
    }
}
