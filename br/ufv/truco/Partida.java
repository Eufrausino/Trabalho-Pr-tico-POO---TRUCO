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

	public void imprimePlacar(int vitorias1, int vitorias2) {
		System.out.printf("[%s] %d jogo(s) - %d jogo(s) [%s]\n", equipe1, vitorias1,
			vitorias2, equipe2);
	}

	public void executaPartida() {
		int numJogos = 0;
		int vitorias1 = 0, vitorias2 = 0;
		while(vitorias1 < 2 && vitorias2 < 2) {
			++numJogos;
			Jogo jogo = new Jogo(equipe1, equipe2);
			int eq = jogo.executajogo(baralho);
			jogos.add(jogo);
			if(eq == 1) ++vitorias1;
			else ++vitorias2;
			System.out.printf("\nJOGO #%d encerrado! Placar atual:\n", numJogos);
			imprimePlacar(vitorias1, vitorias2);
		}
		equipeVencedora = vitorias1 > vitorias2 ? equipe1 : equipe2;
		System.out.printf("PARTIDA encerrada! A equipe %s ganhou!\n", equipeVencedora);
	}
}
