package br.ufv.truco.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.ufv.truco.Carta;
import br.ufv.truco.Jogador;
import br.ufv.truco.Naipe;
import br.ufv.truco.Resposta;

public class JogadorTeste {
	
	@Test
	public void testeToString() {
		Jogador jogador = new Jogador("J1");
		assertEquals("J1", jogador.toString());
	}
	
	@Test
	public void testeRecebeCarta() {
		Jogador jogador = new Jogador("J1");
		
		jogador.recebeCarta(new Carta(1, Naipe.COPAS));
		assertEquals(1, jogador.getCartas().size());
	}
	
	@Test
	public void testeLargaCartas() {
		Jogador jogador = new Jogador("J1");
		
		jogador.recebeCarta(new Carta(1, Naipe.COPAS));
		jogador.recebeCarta(new Carta(3, Naipe.ESPADAS));
		jogador.largaCartas();
		assertTrue(jogador.getCartas().isEmpty());
	}
	
	@Test
	public void testeAge() {
		Jogador jogador = new Jogador("J1");
		Resposta r = jogador.age(false);
		assertTrue(r == Resposta.ACEITA || r == Resposta.AUMENTA || r == Resposta.CORRE);
		r = jogador.age(true);
		assertTrue(r == Resposta.ACEITA || r == Resposta.CORRE);
	}
	
	@Test
	public void testeResponde() {
		Jogador jogador = new Jogador("J1");
		Resposta r = jogador.responde();
		assertTrue(r == Resposta.ACEITA || r == Resposta.AUMENTA || r == Resposta.CORRE);
	}
	
	@Test
	public void testeJogaCarta() {
		Jogador jogador = new Jogador("J1");
		jogador.recebeCarta(new Carta(1, Naipe.COPAS));
		jogador.recebeCarta(new Carta(3, Naipe.ESPADAS));
		jogador.recebeCarta(new Carta(6, Naipe.OUROS));
		jogador.recebeCarta(new Carta(3, Naipe.PAUS));
		assertEquals(Carta.class, jogador.jogaCarta(false, false).getClass());
		assertEquals(Carta.class, jogador.jogaCarta(false, true).getClass());
		assertEquals(Carta.class, jogador.jogaCarta(true, false).getClass());
		assertEquals(Carta.class, jogador.jogaCarta(true, true).getClass());
	}
	
	
}
