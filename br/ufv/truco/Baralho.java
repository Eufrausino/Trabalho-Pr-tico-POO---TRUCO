package br.ufv.truco;

import java.util.Arrays;
import java.util.Random;

public class Baralho {
    private int cartasRestantes;
    private boolean[] retirada = new boolean[40];
    private Random rand = new Random();

    public Baralho() {
        this.reiniciar();
    }

    public void reiniciar() {
        cartasRestantes = 40;
        Arrays.fill(retirada, false);
    }

    private boolean foiRetirada(int valor, int naipe) {
        return retirada[naipe * 10 + valor - 1];
    }

    private void retira(int valor, int naipe) {
        --cartasRestantes;
        retirada[naipe * 10 + valor - 1] = true;
    }

    public Carta retiraCartaAleatoria() {
        // Baralho vazio, não tem como retornar carta nenhuma
        if(cartasRestantes == 0) return null;
        int valor, naipeNum;
        while(true) {
            valor = rand.nextInt(10) + 1;
            naipeNum = rand.nextInt(4);
            if(!foiRetirada(valor, naipeNum))
                break;
        }
        Naipe naipe;
        switch(naipeNum) {
            case 0: naipe = Naipe.ESPADAS; break;
            case 1: naipe = Naipe.COPAS; break;
            case 2: naipe = Naipe.OUROS; break;
            case 3: naipe = Naipe.PAUS; break;
            default:
                naipe = Naipe.PAUS; // cala a boca LSP
                System.err.println("[?] estranho...");
                break;
        }
        Carta c = new Carta(valor, naipe);
        retira(valor, naipeNum);
        return c;
    }

    public int getCartasRestantes() {
        return cartasRestantes;
    }
}
