package br.ufv.truco;

public class Equipe {
    private int pontos;
    private Jogador jogador1;
    private Jogador jogador2; // pode ser nulo

    public Equipe(Jogador jogadorSozinho) {
        jogador1 = jogadorSozinho;
        jogador2 = null;
        pontos = 0;
    }

    public Equipe(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        pontos = 0;
    }

    @Override
    public String toString() {
        String equipe = jogador1.toString();
        if(jogador2 != null)
            equipe += " e " + jogador2.toString();
        return equipe;
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

    public void adicionaPontos(int pontos) {
        this.pontos += pontos;
    }

    public void reiniciaPontos() {
        pontos = 0;
    }
}
