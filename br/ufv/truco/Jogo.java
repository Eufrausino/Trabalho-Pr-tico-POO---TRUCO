package br.ufv.truco;
import java.util.ArrayList;

public class Jogo {
    public Baralho baralho = new Baralho();
    private ArrayList<Mao> maos = new ArrayList<>();

    public ArrayList<Mao> getMaos() {
        return this.maos;
    }

    public int executajogo(Equipe equipe1, Equipe equipe2) {
        int jogadorInicial = 0;
        equipe1.reiniciaPontos();
        equipe2.reiniciaPontos();
        // Enquanto as equipes tiverem menos de 12 pontos, o jogo continua
        while(equipe1.getPontos() < 12 && equipe2.getPontos() < 12) {
            // Executa uma mão convencional com as equipes
            Mao mao = new Mao(equipe1, equipe2);
            int eq = mao.executaMao(jogadorInicial++, baralho);
            maos.add(mao);

            // Aumenta a pontuação da equipe adequada
            int pontos = mao.getValor();
            if(eq == 1) equipe1.adicionaPontos(pontos);
            else equipe2.adicionaPontos(pontos);
        }
        return equipe1.getPontos() > equipe2.getPontos() ? 1 : 2;
    }
}
