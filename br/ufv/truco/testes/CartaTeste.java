package br.ufv.truco.testes;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import br.ufv.truco.Carta;
import br.ufv.truco.Naipe;

public class CartaTeste {
    
	@Test
	public void testeToString() {
		Naipe naipes[] = new Naipe[4];
		naipes[0] = Naipe.COPAS;
		naipes[1] = Naipe.ESPADAS;
		naipes[2] = Naipe.OUROS;
		naipes[3] = Naipe.PAUS;
		Carta carta;
		String valorTexto;
		for (int i = 1; i <= 10; i++) {
			for (Naipe naipe : naipes) {
				carta = new Carta(i, naipe);
				switch(i) {
	            case 1: valorTexto = "A"; break;
	            case 8: valorTexto = "Q"; break;
	            case 9: valorTexto = "J"; break;
	            case 10: valorTexto = "K"; break;
	            default:
	                valorTexto = Integer.toString(i);
	                break;
	        }
				assertEquals(valorTexto + "-" + naipe.name().charAt(0), carta.toString());
			}
		}
	}
	
	@Test
	public void testeCompara() {
		Carta carta1 = new Carta(1, Naipe.COPAS);
		Carta carta2 = new Carta(4, Naipe.PAUS);
		Carta carta3 = new Carta(6, Naipe.OUROS);
		
		assertEquals(-1, carta3.compara(carta1));
		assertEquals(0, carta1.compara(carta1));
		assertEquals(1, carta2.compara(carta3));
	}
}
