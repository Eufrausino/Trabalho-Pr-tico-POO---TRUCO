package br.ufv.truco;

import java.util.ArrayList;
import java.util.Scanner;

public class Jogador {
    private String nome;

    private ArrayList<Carta> cartas;

    private boolean ehMaquina;

    private Scanner scan = new Scanner(System.in);

    public Jogador(String nome) {
        this.nome = nome;
        this.ehMaquina = false;
        this.cartas = new ArrayList<Carta>();
    }

    public Jogador(String nome, boolean ehMaquina) {
        this.nome = nome;
        this.ehMaquina = ehMaquina;
        this.cartas = new ArrayList<Carta>();
    }

    public void receberCartas(Carta carta) {
        this.cartas.add(carta);
    }

    // public void pedirTruco() {}

    public Resposta resposta() {
        if(ehMaquina) return Resposta.ACEITA; // mudar isso
        int resp;
        while(true) {
            System.out.println("-> Corre, aceita ou pede mais?");
            System.out.println("Correr (0), aceitar (1), pedir mais (2) => ");
            resp = scan.nextInt();
            switch(resp) {
                case 0:
                    System.out.println("Correu!");
                    return Resposta.CORRE;
                case 1:
                    System.out.println("Aceitou!");
                    return Resposta.ACEITA;
                case 2:
                    return Resposta.AUMENTA;
                default:
                    System.err.println("[!] Resposta inválida, tente novamente");
                    break;
            }
        }
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
            s.append(i + ". [" + carta + "] ");
            ++i;
        }
        System.out.println(s.toString());
        while(true) {
            System.out.println("-> Qual carta deseja jogar? ");
            indiceEscolhida = scan.nextInt();
            if(indiceEscolhida >= 1 && indiceEscolhida <= i) break;
            else System.err.println("[!] Carta inválida! Escolha novamente");
        }
        Carta c = cartas.remove(indiceEscolhida - 1);
        c.setEncoberta(coberta);
        return c;
    }

    public void setCartas(ArrayList<Carta> arrayList) {
    }
}
