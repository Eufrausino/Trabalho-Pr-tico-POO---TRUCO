package br.ufv.truco.testes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.ufv.truco.Jogador;
import br.ufv.truco.Rodada;

public class TesteRodada {

	// Não funciona caso tenha que jogar uma carta (ocorre um erro)
	@Test
	public void testeExecutaRodada() {
		Jogador jogadores[] = new Jogador[4];
		jogadores[0] = new Jogador("J1");
		jogadores[1] = new Jogador("J2");
		jogadores[2] = new Jogador("J3");
		jogadores[3] = new Jogador("J4");
		
		Rodada rodada = new Rodada(1, 4, jogadores);
		int r = rodada.executaRodada(1);
		assertTrue(r == 1 || r == 2);
		
	}
}
