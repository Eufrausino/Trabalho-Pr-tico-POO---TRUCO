package br.ufv.truco.testes;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import br.ufv.truco.Naipe;

import java.util.Iterator;

import br.ufv.truco.Baralho;
import br.ufv.truco.Carta;

public class BaralhoTeste {
	@Test
	public void testeReiniciar() {
		Baralho baralho = new Baralho();
		baralho.reiniciar();
		assertEquals(40, baralho.getCartasRestantes());
	}
	
	@Test
	public void testeRetiraCartaAleatoria() {
		Baralho baralho = new Baralho();
		Carta carta;
		for (int i = 0; i < 40; i++) {
			carta = baralho.retiraCartaAleatoria();
			assertTrue((carta.getValor() >= 1 && carta.getValor() <= 10));
		}
		assertNull(baralho.retiraCartaAleatoria());
	}
	
	@Test
	public void testeReiniciar_RetiraCartaAleatoria() {
		Baralho baralho = new Baralho();
		for (int i = 0; i < 10; i++) {
			baralho.retiraCartaAleatoria();
		}
		assertEquals(30, baralho.getCartasRestantes());
		baralho.reiniciar();
		assertEquals(40, baralho.getCartasRestantes());
	}
}
