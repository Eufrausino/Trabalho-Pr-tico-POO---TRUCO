package br.ufv.truco;

public class Equipe {
    private int id;
    private int pontos;
    private Jogador jogador1;
    private Jogador jogador2; // pode ser nulo

    public Equipe(int id, Jogador jogadorSozinho) {
        this.id = id;
        jogador1 = jogadorSozinho;
        jogador2 = null;
        pontos = 0;
    }

    public Equipe(int id, Jogador jogador1, Jogador jogador2) {
        this.id = id;
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        pontos = 0;
    }

    public int getId() {
        return id;
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public boolean ehDupla() {
        return jogador2 != null;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public int getPontos() {
        return pontos;
    }
}
