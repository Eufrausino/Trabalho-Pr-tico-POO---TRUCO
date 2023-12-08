package br.ufv.truco;
import java.util.ArrayList;

public class Jogo {
    private Equipe equipe1;
    private Equipe equipe2;
    private ArrayList<Mao> maos = new ArrayList<>();

    public Jogo(Equipe equipe1, Equipe equipe2) {
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
    }

    public ArrayList<Mao> getMaos() {
        return this.maos;
    }

    public int executajogo(Baralho baralho) {
        int conta = 1;
        int jogadorInicial = 0;
        equipe1.reiniciaPontos();
        equipe2.reiniciaPontos();
        // Enquanto as equipes tiverem menos de 12 pontos, o jogo continua
        while(equipe1.getPontos() < 12 && equipe2.getPontos() < 12) {
            // Executa uma mão convencional com as equipes
            Mao mao = new Mao(equipe1, equipe2);
            int eq = mao.executaMao(jogadorInicial, baralho);
            ++jogadorInicial;
            maos.add(mao);

            // Aumenta a pontuação da equipe adequada
            int pontos = mao.getValor();
            if(eq == 1) equipe1.adicionaPontos(pontos);
            else equipe2.adicionaPontos(pontos);
            System.out.printf("Mão #%d encerrada\n", conta++);
            System.out.printf("Equipe 1 tem %d pontos\n", equipe1.getPontos());
            System.out.printf("Equipe 2 tem %d pontos\n", equipe2.getPontos());
        }
        return equipe1.getPontos() > equipe2.getPontos() ? 1 : 2;
    }
}
