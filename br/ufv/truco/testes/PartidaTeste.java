package br.ufv.truco.testes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.ufv.truco.Equipe;
import br.ufv.truco.Jogador;
import br.ufv.truco.Partida;

public class PartidaTeste {

	@Test
	public void testeExecutaPartida() {
		Equipe e1 = new Equipe(new Jogador("J1"));
		Equipe e2 = new Equipe(new Jogador("J2"));
		Partida partida = new Partida(e1, e2);
		partida.executaPartida();
		Equipe r = partida.getEquipeVencedora();
		assertTrue(r == e1 || r == e2);
	}
}
