package br.ufv.truco;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Truco {
	public static Scanner scan = Utils.scan;

	public static Jogador lerJogador(int id) {
		System.out.printf("Nome do jogador #%d: ", id);
		String nome = scan.nextLine();
		// scan.nextLine(); // remove quebra de linha sobressalente
		return new Jogador(nome);
	}

	public static void main(String[] args) {
		int numJogadores;

		System.out.println("== TRUCO JAVA ==");
		System.out.println("Bem-vindo ao truco mineiro da ilha de Java!");
		while(true) {
			try {
				System.out.print("Quantos jogadores vão participar? (2 ou 4) ");
				numJogadores = scan.nextInt();
				if(numJogadores == 2 || numJogadores == 4) break;
				System.err.println("[!] Número inválido de jogadores");
			} catch(InputMismatchException ex) {
				System.err.println("[!] Inteiro inválido");
				scan.nextLine(); // remove '\n' sobressalente
			}
		}
		scan.nextLine(); // remove '\n' sobressalente
		System.out.println();

		// Lendo os jogadores
		Jogador[] jogadores = new Jogador[numJogadores];
		if(numJogadores == 4) System.out.println("Equipe 1:");
		for(int i = 0; i < numJogadores; ++i) {
			if(i == 2) System.out.println("Equipe 2:");
			jogadores[i] = lerJogador(i + 1);
		}

		// Definindo as equipes
		Equipe equipe1, equipe2;
		if(numJogadores == 2) {
			equipe1 = new Equipe(jogadores[0], null);
			equipe2 = new Equipe(jogadores[1], null);
		} else {
			equipe1 = new Equipe(jogadores[0], jogadores[1]);
			equipe2 = new Equipe(jogadores[2], jogadores[3]);
		}

		// Instanciando e executando a partida
		Utils.passarProProximo();
		Partida partida = new Partida(equipe1, equipe2);
		partida.executaPartida();
	}
}
