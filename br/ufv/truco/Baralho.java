package br.ufv.truco;

import java.util.Random;

public class Baralho {
    private int cartasRestantes;
    private Carta[] cartas = new Carta[40];
    private boolean[] jogada = new boolean[40];

    public Baralho() {
        cartasRestantes = 40;
        // Inicializa todas as cartas
        for(int base = 0; base < 40; base += 10)
            for(Naipe n : Naipe.values())
                for(int v = 1; v <= 10; ++v)
                    cartas[base + v] = new Carta(v, n);
        // Inicializa o vetor jogadas
        for(int i = 0; i < 40; ++i)
            jogada[i] = false;
    }

    public void reiniciar() {
        cartasRestantes = 40;
        // Reinicializa o vetor jogadas
        for(int i = 0; i < 40; ++i)
            jogada[i] = false;
    }

    public Carta retiraCartaAleatoria() {
        // Baralho vazio, não tem como retornar carta nenhuma
        if(cartasRestantes == 0) return null;
        Random rand = new Random();
        int i = rand.nextInt(40);
        // Se a carta já tiver sido jogada, repita a geração do índice
        // aleatório até encontrar uma que não tenha sido
        while(jogada[i]) i = rand.nextInt(40);
        --cartasRestantes;
        jogada[i] = true;
        return cartas[i];
    }

    public int getCartasRestantes() {
        return cartasRestantes;
    }
}
