package br.ufv.truco.testes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.ufv.truco.Baralho;
import br.ufv.truco.Equipe;
import br.ufv.truco.Jogador;
import br.ufv.truco.Mao;

public class MaoTeste {

	@Test
	public void testeExecutaMao() {
		Mao mao = new Mao(new Equipe(new Jogador("J1")), new Equipe(new Jogador("J2")));
		int r = mao.executaMao(1, 1, new Baralho());
		assertTrue(r == 1 || r == 2);
	}
}
