package br.ufv.truco;
import java.util.ArrayList;

public class Partida {
	private Equipe equipe1;
	private Equipe equipe2;
	private Equipe equipeVencedora;
	private ArrayList<Jogo> jogos;
	private Baralho baralho = new Baralho();

	public Partida(Equipe equipe1, Equipe equipe2) {
		jogos = new ArrayList<Jogo>();
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;
	}

	public Equipe getEquipeVencedora() {
		return equipeVencedora;
	}

	public void executaPartida() {
		int vitorias1 = 0, vitorias2 = 0;
		while(vitorias1 < 2 && vitorias2 < 2) {
			Jogo jogo = new Jogo(equipe1, equipe2);
			int eq = jogo.executajogo(baralho);
			jogos.add(jogo);
			if(eq == 1) ++vitorias1;
			else ++vitorias2;
		}
		equipeVencedora = vitorias1 > vitorias2 ? equipe1 : equipe2;
	}
}
