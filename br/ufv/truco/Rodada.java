package br.ufv.truco;
import java.util.Arrays;

public class Rodada {
	// O "nível" de truco da rodada, de 0 a 3
	private int nivelTruco;

	// O desfecho de uma rodada pode decidir o desfecho da mão diretamente,
	// esse atributo reflete isso
	private boolean decisiva = false;

	// A carta que venceu a rodada, nula em caso de empate ou vitória por truco
	private Carta cartaVencedora = null;

	// A quantidade de jogadores envolvidos na rodada
	private int numJogadores;

	// Os jogadores envolvidos na rodada e as cartas jogadas até o momento
	private Jogador[] jogadores;
	private Carta[] cartasJogadas;

	public Rodada(int nivelInicialTruco, int numJogadores, Jogador[] jogadores) {
		this.nivelTruco = nivelInicialTruco;
		this.numJogadores = numJogadores;
		this.jogadores = jogadores;
		// Todas as cartas começam nulas, pois ainda não houve jogo
		this.cartasJogadas = new Carta[numJogadores];
		Arrays.fill(cartasJogadas, null);
	}

	public int getNivelTruco() {
		return nivelTruco;
	}

	public boolean decideMao() {
		return decisiva;
	}

	public Carta getCartaVencedora() {
		return cartaVencedora;
	}

	// Pede truco, aumentando o valor da rodada. Retorna false caso não seja
	// possível pedir truco; do contrário, efetua a operação e retorna true
	private boolean pedeTruco() {
		if(nivelTruco >= 4) return false;
		++nivelTruco;
		switch(nivelTruco) {
			case 1:
				System.out.println("TRUCO!!!");
				break;
			case 2:
				System.out.println("MEEEEEEEEI PAUUUU!");
				break;
			case 3:
				System.out.println("NOOOOOOOOOVE!");
				break;
			case 4:
				System.out.println("*Doze*.");
				break;
		}
		return true;
	}

	// Um confronto de truco é iniciado por um jogador (o ataque) e demanda a
	// resposta de outro (a defesa). É a única forma de aumentar o valor de uma
	// rodada, podendo também culminar no fim da partida
	private ResultadoTruco confrontoTruco(Jogador ataque, Jogador defesa) {
		// Caso não seja possível trucar, nada acontece
		if(!pedeTruco()) return ResultadoTruco.ACEITO;
		while(true) {
			// Resposta da defesa
			while(true) {
				Resposta def = defesa.responde();
				switch(def) {
					case ACEITA:
						return ResultadoTruco.ACEITO;
					case AUMENTA:
						if(!pedeTruco()) {
							System.err.println("[!] Não pode aumentar! >:(");
							continue;
						}
						break;
					case CORRE:
						return ResultadoTruco.DEFESA_CORRE;
				}
				break;
			}
			// Reação do atacante!
			while(true) {
				Resposta atq = ataque.responde();
				switch(atq) {
					case ACEITA:
						return ResultadoTruco.ACEITO;
					case AUMENTA:
						if(!pedeTruco()) {
							System.err.println("[!] Não pode aumentar! >:(");
							continue;
						}
						break;
					case CORRE:
						return ResultadoTruco.ATAQUE_CORRE;
				}
				break;
			}
		}
	}

	// Determina o índice da maior carta no vetor das cartas jogadas; esse
	// índice é utilizado para determinar o jogador vencedor
	private int declaraVencedor(int posInicial) {
		int indiceMaior = posInicial;
		Carta maior = cartasJogadas[posInicial];
		for(int i = 1; i < numJogadores; ++i) {
			int pos = (posInicial + i) % numJogadores;
			Carta carta = cartasJogadas[pos];
			if(carta.ganhaDe(maior)) {
				indiceMaior = pos;
				maior = carta;
			}
		}
		// Confere se houve um empate
		boolean houveEmpate = false;
		int indiceEmpate = posInicial;
		for(int i = 0; i < numJogadores; ++i) {
			int pos = (posInicial + i) % numJogadores;
			if(pos == indiceMaior) continue;
			Carta carta = cartasJogadas[pos];
			if(maior.getValor() == carta.getValor()) {
				// A carta deve ser igual em valor, mas não superior em "poder" para que
				// ocorra um empate
				if(!maior.ganhaDe(carta)) {
					houveEmpate = true;
					indiceEmpate = pos;
					break;
				}
			}
		}
		return houveEmpate ? -1 * (indiceEmpate + 1) : indiceMaior;
	}

	private void imprimeCartas() {
		System.out.println("== Cartas na mesa ==");
		for(int i = 0; i < numJogadores; ++i) {
			if(cartasJogadas[i] != null)
				System.out.printf("(%s) %s\n", jogadores[i], cartasJogadas[i]);
		}
		System.out.println();
	}

	// Executa a rodada, partindo de um jogador
	public int executaRodada(int posInicial) {
		return executaRodada(posInicial, false, true);
	}

	// Executa a rodada, partindo de um jogador em particular, com a opção de
	// poder trucar ou não (útil para a mão de dez) e de ver as cartas ou não
	// (útil para a mão de ferro)
	public int executaRodada(int posInicial, boolean naoPodeTrucar, boolean verCartas) {
		// Laço de execução da rodada, itera sobre os jogadores existentes
		for(int turno = 0; turno < numJogadores; ++turno) {
			int posAtual = (posInicial + turno) % numJogadores;
			int posProximo = (posAtual + 1) % numJogadores;
			Jogador atual = jogadores[posAtual], proximo;
			if(turno == numJogadores - 1) naoPodeTrucar = true;

			Carta c;
			Resposta resp = atual.age(naoPodeTrucar);
			switch(resp) {
				case ACEITA:
					c = atual.jogaCarta(turno >= 1, verCartas);
					cartasJogadas[posAtual] = c;
					break;
				case AUMENTA:
					proximo = jogadores[posProximo];
					ResultadoTruco res = confrontoTruco(atual, proximo);
					switch(res) {
						case ACEITO:
							c = atual.jogaCarta(turno >= 1, verCartas);
							cartasJogadas[posAtual] = c;
							break;
						case ATAQUE_CORRE:
							decisiva = true;
							return posProximo; // a mão encerra com uma derrota para o ataque
						case DEFESA_CORRE:
							decisiva = true;
							return posAtual; // a mão encerra com uma vitória para o ataque
					}
					break;
				case CORRE:
					decisiva = true;
					return posProximo; // a mão encerra com uma derrota para o jogador atual
			}
			imprimeCartas();
		}
		int v = declaraVencedor(posInicial);
		// Se o índice é negativo, houve um empate, que deve ser tratado pela mão
		if(v < 0) {
			System.out.printf("O jogador %s cangou...\n", jogadores[-1 * (v + 1)]);
			return v;
		}
		// Sendo o índice positivo, temos um claro vencedor
		System.out.printf("O jogador %s venceu a rodada!\n", jogadores[v]);
		Carta cartaVencedora = cartasJogadas[v];
		this.cartaVencedora = cartaVencedora;
		return v;
	}
}
