package br.ufv.truco;

import java.util.ArrayList;
import java.util.Scanner;

public class Jogador {
    private String nome;

    private ArrayList<Carta> cartas;

    private boolean ehMaquina;

    private Scanner scan;

    public Jogador(String nome, boolean ehMaquina) {
        this.nome = nome;
        this.ehMaquina = ehMaquina;
        this.cartas = new ArrayList<Carta>();
    }

    public void receberCartas(Carta[] cartas) {
        for(int i = 0; i < 3; ++i)
            this.cartas.add(i, cartas[i]);
    }

    // public void pedirTruco() {}

    public char resposta(Jogador alvo) {
        if(ehMaquina) return 's'; // mudar isso
        System.out.print("-> Resposta: ");
        return (char) scan.nextByte();
    }

    public String gritar(Jogador alvo) {
        if(ehMaquina) return "AAAAAA";
        System.out.print("-> Grito sinistro muito foda: ");
        String grito = scan.nextLine();
        return grito;
    }

    public Carta jogarCarta(boolean coberta) {
        int i = 1, indiceEscolhida;
        System.out.printf("== Jogar carta (%s) ==\n", nome);
        StringBuilder s = new StringBuilder();
        for(Carta carta : cartas) {
            s.append(i + ". " + carta + " ");
            ++i;
        }
        System.out.println(s.toString());
        while(true) {
            System.out.println("-> Qual carta deseja jogar? ");
            indiceEscolhida = scan.nextInt();
            if(indiceEscolhida >= 1 && indiceEscolhida <= i) break;
            else System.err.println("[!] Carta inválida! Escolha novamente");
        }
        return cartas.remove(indiceEscolhida);
    }
}
