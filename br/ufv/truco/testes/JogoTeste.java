package br.ufv.truco.testes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.ufv.truco.Baralho;
import br.ufv.truco.Equipe;
import br.ufv.truco.Jogador;
import br.ufv.truco.Jogo;

public class JogoTeste {
	
	@Test
	public void testeExecutaJogo() {
		Jogo jogo = new Jogo(new Equipe(new Jogador("J1")), new Equipe(new Jogador("J2")));
		Baralho baralho = new Baralho();
		int r = jogo.executajogo(baralho);
		assertTrue(r == 1 || r == 2);
	}
}
