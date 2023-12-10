package br.ufv.truco.testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.ufv.truco.Equipe;
import br.ufv.truco.Jogador;

public class EquipeTeste {

	@Test
	public void testeToString() {
		Equipe equipe1 = new Equipe(new Jogador("J1"));
		Equipe equipe2 = new Equipe(new Jogador("J3"), new Jogador("J4"));
		
		assertEquals("J1", equipe1.toString());
		assertEquals("J3 e J4", equipe2.toString());
	}
	
	@Test
	public void testeEhDupla() {
		Equipe equipe1 = new Equipe(new Jogador("J1"));
		Equipe equipe2 = new Equipe(new Jogador("J3"), new Jogador("J4"));
		
		assertEquals(false, equipe1.ehDupla());
		assertEquals(true, equipe2.ehDupla());
	}
	
	@Test
	public void testeAdicionaPontos() {
		Equipe equipe1 = new Equipe(new Jogador("J1"));
		
		equipe1.adicionaPontos(2837);
		assertEquals(2837, equipe1.getPontos());
	}
	
	@Test
	public void testeReiniciaPontos() {
		Equipe equipe1 = new Equipe(new Jogador("J1"));
		
		equipe1.adicionaPontos(2837);
		equipe1.reiniciaPontos();
		assertEquals(0, equipe1.getPontos());
	}
}
