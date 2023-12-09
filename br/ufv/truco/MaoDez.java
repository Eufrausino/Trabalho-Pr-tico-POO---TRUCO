package br.ufv.truco;

// Variante da mão comum que ocorre quando uma das equipes tem dez pontos
public class MaoDez extends Mao {

    public MaoDez(Equipe e1, Equipe e2) {
        super(e1, e2);
    }

    @Override
    // A mão de dez tem sempre valor 4
    protected int calculaValor(int nivelTruco) {
        return 4;
    }

    @Override
    // Na mão de dez, pedir truco não é permitido
    public int executaMao(int posInicial, int ganhadorAnterior, Baralho baralho) {
        return executaMao(posInicial, ganhadorAnterior, true, true, baralho);
    }
}
