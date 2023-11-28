package br.ufv.truco;
import java.util.ArrayList;

public class Partida {
	private ArrayList<Jogo> jogos;
	private Baralho baralho;
	private Equipe equipe1;
	private Equipe equipe2;
	private Equipe equipeVencedora;


	public Partida(Equipe equipe1,Equipe equipe2, Baralho baralho) {
		jogos = new ArrayList<Jogo>();
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;
		this.baralho = baralho;
	}

	public Equipe getEquipeVencedora() {
		return equipeVencedora;
	}

	public void defineVencedorPartida() {
		int pontosEq1 = 0, pontosEq2 = 0;
		for(int i = 0; i < jogos.size(); i++){
			jogos.get(i).getEquipeVencedora();
			if(equipe1 == jogos.get(i).getEquipeVencedora()) {
				pontosEq1++;
			}
			else if(equipe2 == jogos.get(i).getEquipeVencedora()) {
				pontosEq2++;
			}
		}
		if(pontosEq1 > pontosEq2) {
			equipeVencedora = equipe1;
		}
		else if(pontosEq1 == pontosEq2) {
			equipeVencedora = null;
		}
		else {
			equipeVencedora = equipe2;
		}
	}

	public void executaPartida() {
		int count = 0;
		while(jogos.size()<3) {
			jogos.add(new Jogo());
			jogos.get(count).executajogo(equipe1, equipe2);
		}

		defineVencedorPartida();
	}
}
