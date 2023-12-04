package br.ufv.truco;

public class Truco {
	public static void main(String[] args) {

		// instanciando a tela do jogo
		// TODO TelaDeJogo

		// instanciando os jogadores
		Jogador jogador1 = new Jogador("J1");
		Jogador jogador2 = new Jogador("J2");
		Jogador jogador3 = new Jogador("J3");
		Jogador jogador4 = new Jogador("J4");

		// instanciando as equipes
		Equipe equipe1 = new Equipe(1, jogador1, jogador2);
		Equipe equipe2 = new Equipe(2, jogador3, jogador4);

		// instanciando o baralho
		Baralho baralho = new Baralho();

		// instanciando a partida
		Partida partida = new Partida(equipe1, equipe2, baralho);

		partida.executaPartida();
	}
}
