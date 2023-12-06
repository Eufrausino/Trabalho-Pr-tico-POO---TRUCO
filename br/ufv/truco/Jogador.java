package br.ufv.truco;

import java.util.ArrayList;
import java.util.Scanner;

public class Jogador {
    private String nome;

    private ArrayList<Carta> cartas = new ArrayList<>();

    private boolean ehMaquina = false;

    private Scanner scan = new Scanner(System.in);

    public Jogador(String nome) {
        this.nome = nome;
    }

    public Jogador(String nome, boolean ehMaquina) {
        this.nome = nome;
        this.ehMaquina = ehMaquina;
    }

    public void largaCartas() {
        cartas.clear();
    }

    public void recebeCarta(Carta carta) {
        cartas.add(carta);
    }

    public Resposta age(boolean naoPodeTrucar) {
        System.out.printf("== Jogador (%s) ==\n", nome);
        if(naoPodeTrucar == false)
        {
            while(true) {
            System.out.println("Qual ação você deseja fazer?");
            System.out.print("(1) jogar carta, (2) pedir truco, (3) gritar, (4) correr => ");
            int resp = scan.nextInt();
            switch(resp) {
                case 1:
                    return Resposta.ACEITA;
                case 2:
                    return Resposta.AUMENTA;
                case 3:
                    grita();
                    break;
                case 4:
                    return Resposta.CORRE;
                default:
                    System.err.println("[!] Resposta não reconhecida");
            }
         }
        }
        else
        {
            while(true) {
            System.out.println("Qual ação você deseja fazer?");
            System.out.print("(1) jogar carta,(2) gritar,(3) correr => ");
            int resp = scan.nextInt();
            switch(resp) {
                case 1:
                    return Resposta.ACEITA;
                case 2:
                    grita();
                    break;
                case 3:
                    return Resposta.CORRE;
                default:
                    System.err.println("[!] Resposta não reconhecida");
            }
         }
        }
    }

    public Resposta responde() {
        if(ehMaquina) return Resposta.ACEITA; // mudar isso
        while(true) {
            System.out.printf("== Responder (%s) ==\n", nome);
            System.out.println("Corre, aceita ou pede mais?");
            System.out.print("(1) correr, (2) aceitar, (3) pedir mais => ");
            int resp = scan.nextInt();
            switch(resp) {
                case 1:
                    System.out.println("Correu!");
                    return Resposta.CORRE;
                case 2:
                    System.out.println("Aceitou!");
                    return Resposta.ACEITA;
                case 3:
                    return Resposta.AUMENTA;
                default:
                    System.err.println("[!] Resposta inválida, tente novamente");
            }
            System.out.println();
        }
    }

    public void grita() {
        String grito;
        if(ehMaquina) {
            grito = "AAAAAA";
        } else {
            System.out.print("Grito sinistro: ");
            scan.nextLine(); // consome um '\n' que com certeza já estará na entrada
            grito = scan.nextLine();
        }
        System.out.println(nome + " bate na mesa e grita: " + grito + "\n");
    }

    public Carta jogaCarta(boolean podeEncoberta) {
        int i = 1, indiceEscolhida;
        System.out.printf("-- Jogar carta (%s) --\n", nome);
        StringBuilder s = new StringBuilder();
        for(Carta carta : cartas) {
            s.append(i + ". [" + carta + "] ");
            ++i;
        }
        System.out.println(s.toString());
        while(true) {
            System.out.print("Carta a jogar => ");
            indiceEscolhida = scan.nextInt();
            if(indiceEscolhida >= 1 && indiceEscolhida <= i) break;
            else System.err.println("[!] Carta inválida! Escolha novamente");
        }
        boolean continua = true;
        Carta c = cartas.remove(indiceEscolhida - 1);
        if(podeEncoberta) {
            while(continua) {
                System.out.print("(1) jogar normalmente, (2) jogar encoberta => ");
                int resp = scan.nextInt();
                switch(resp) {
                    case 1:
                        c.setEncoberta(false);
                        continua = false;
                        break;
                    case 2:
                        c.setEncoberta(true);
                        continua = false;
                        break;
                    default:
                        System.err.println("[!] Resposta não reconhecida");
                }
            }
        }
        System.out.println();
        return c;
    }
}
