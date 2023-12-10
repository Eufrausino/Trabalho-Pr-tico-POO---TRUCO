package br.ufv.truco;

// Variante da mão que ocorre quando ambas equipes têm dez pontos
public class MaoFerro extends Mao {

    public MaoFerro(Equipe eq1, Equipe eq2) {
        super(eq1, eq2);
    }

    @Override
    // A mão de ferro tem sempre valor 2
    protected int calculaValor(int nivelTruco) {
        return 2;
    }

    @Override
    // Na mão de ferro, pedir truco não é permitido e os jogadores não
    // pode ver as suas cartas antes de jogar
    public int executaMao(int posInicial, int ganhadorAnterior, Baralho baralho) {
        return executaMao(posInicial, ganhadorAnterior, true, false, baralho,
         true);
    }
}