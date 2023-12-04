package br.ufv.truco;
import java.util.ArrayList;

public class Mao {
    private ArrayList<Rodada> rodadas;
    private ArrayList<Jogador> ordemjogadores;
    private int valor;

    public Mao() {
        this.rodadas = new ArrayList<Rodada>();
        this.ordemjogadores = new ArrayList<Jogador>();
        this.valor = 2;
    }

    public ArrayList<Rodada> getRodadas() {
        return this.rodadas;
    }

    public ArrayList<Jogador> getOrdemjogadores() {
        return this.ordemjogadores;
    }

    public int getValor() {
        return this.valor;
    }

    public void setRodadas(ArrayList<Rodada> rodadas) {
        this.rodadas = rodadas;
    }

    public void setOrdemjogadores(ArrayList<Jogador> ordemjogadores) {
        this.ordemjogadores = ordemjogadores;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void distribuiCartas(ArrayList<Jogador> jogadores, Baralho baralho) {
        // Embaralha o baralho
        baralho.reiniciar();

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

    //Função que define a ordem dos jogadores na mão, para um jogo de 2 jogadores
    public void defineOrdemJogadores(Jogador jogador1, Jogador jogador2) {
    if ((rodadas.get(rodadas.size() - 1).jogadorVencedor == null) || (rodadas.get(rodadas.size() - 1).jogadorVencedor == jogador1) || (rodadas.size() == 0)){
        this.ordemjogadores.add(jogador1);
        this.ordemjogadores.add(jogador2);
    }

    else {
        this.ordemjogadores.add(jogador2);
        this.ordemjogadores.add(jogador1);
        }
    }

    //Função que define a ordem dos jogadores na mão, para um jogo de 4 jogadores
    public void defineOrdemJogadores(Equipe equipe,Equipe equipe2) {

        if (equipe.getJogador2() == null && equipe2.getJogador2() == null) {
            defineOrdemJogadores(equipe.getJogador1(), equipe2.getJogador1());
        }

        else {

        //JogadorMaiorCarta é o jogador que possui a maior carta da rodada, nome provisório de função
            if ((rodadas.get(rodadas.size() - 1).jogadorVencedor == null) || (rodadas.get(rodadas.size() - 1).jogadorVencedor == equipe.getJogador1()) ||  (rodadas.size() == 0)){
                this.ordemjogadores.add(equipe.getJogador1());
                this.ordemjogadores.add(equipe2.getJogador1());
                this.ordemjogadores.add(equipe.getJogador2());
                this.ordemjogadores.add(equipe2.getJogador2());

            } else if (rodadas.get(rodadas.size() - 1).jogadorVencedor == equipe2.getJogador1()) {
                this.ordemjogadores.add(equipe2.getJogador1());
                this.ordemjogadores.add(equipe.getJogador2());
                this.ordemjogadores.add(equipe2.getJogador2());
                this.ordemjogadores.add(equipe.getJogador1());

            } else if (rodadas.get(rodadas.size() - 1).jogadorVencedor == equipe.getJogador2()) {
                this.ordemjogadores.add(equipe.getJogador2());
                this.ordemjogadores.add(equipe2.getJogador2());
                this.ordemjogadores.add(equipe.getJogador1());
                this.ordemjogadores.add(equipe2.getJogador1());
            } else {
                this.ordemjogadores.add(equipe2.getJogador2());
                this.ordemjogadores.add(equipe.getJogador1());
                this.ordemjogadores.add(equipe2.getJogador1());
                this.ordemjogadores.add(equipe.getJogador2());
            }
        }
    }

    //Função que executa uma Mão
    public void executaMao(Equipe equipe1, Equipe equipe2) {
        int Vequipe1 = 0;
        int Vequipe2 = 0;


        while ((rodadas.size() < 3) || Vequipe1 == 2 || Vequipe2 == 2) {
            Rodada rodada = new Rodada();
            rodada.executaRodada(equipe1, equipe2);
            this.rodadas.add(rodada);

            if (rodada.getEquipeVencedora() == equipe1) {
                Vequipe1++;
            } else {
                Vequipe2++;
            }
        }

    }
}
