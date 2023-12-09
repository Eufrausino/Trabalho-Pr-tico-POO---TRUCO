package br.ufv.truco;

public class Mao10 extends Mao
{
    public Mao10(Equipe e1, Equipe e2){
        super(e1, e2);
    }

    @Override
    protected int calculaValor(int nivelTruco){
        return 4;
    }

    @Override
    public int executaMao(int posInicial, int ganhadorAnterior, Baralho baralho){
        return 1;
    }
}
