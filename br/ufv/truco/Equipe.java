package br.ufv.truco;

public class Equipe {
    private int id;

    private int pontos;

    private Jogador jogador1;

    private Jogador jogador2; // pode ser nulo

    private boolean trucado;

    public Equipe(int id, Jogador jogadorSozinho) {
        this.id = id;
        jogador1 = jogadorSozinho;
        jogador2 = null;
        trucado = false;
        pontos = 0;
    }

    public Equipe(int id, Jogador jogador1, Jogador jogador2) {
        this.id = id;
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        trucado = false;
        pontos = 0;
    }

    public int getId() {
        return id;
    }

    public boolean estaTrucado() {
        return trucado;
    }

    public void setTrucado(boolean trucado) {
        this.trucado = trucado;
    }

    public int getPontos() {
        return pontos;
    }
}
