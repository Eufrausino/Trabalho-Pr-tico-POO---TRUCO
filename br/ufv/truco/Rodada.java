package br.ufv.truco;
import java.util.ArrayList;

public class Rodada
{
	private int trucado = 0;
	private boolean terminaMao = false;
	private Carta cartaVencedora = null;

	public int getTrucado() {
		return trucado;
	}

	public void setTrucado(int trucado) {
		this.trucado = trucado;
	}

	public boolean terminaMao() {
		return terminaMao;
	}

	public Carta getCartaVencedora() {
		return cartaVencedora;
	}

	// Pede truco, aumentando o valor da rodada. Retorna false caso não seja
	// possível pedir truco; do contrário, efetua a operação e retorna true
	private boolean trucar() {
		if(trucado >= 4) return false;
		++trucado;
		switch(trucado) {
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
		if(!trucar()) return ResultadoTruco.ACEITO;
		while(true) {
			// Resposta da defesa
			while(true) {
				Resposta def = defesa.responde();
				switch(def) {
					case ACEITA:
						return ResultadoTruco.ACEITO;
					case AUMENTA:
						if(!trucar()) {
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
						if(!trucar()) {
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
	private int declaraVencedor(ArrayList<Carta> cartas) {
		int indiceMaior = 0;
		Carta maior = cartas.get(0);
		for(int i = 1; i < cartas.size(); i++) {
			if(!maior.ganhaDe(cartas.get(i))) {
				maior = cartas.get(i);
				indiceMaior = i;
			}
		}
		// Confere se houve um empate
		boolean houveEmpate = false;
		for(int i = 0; i < cartas.size(); ++i) {
			if(i == indiceMaior) break;
			if(maior.comparaValor(cartas.get(i)) == 0) {
				houveEmpate = true;
				break;
			}

		}
		return houveEmpate ? -1 : indiceMaior;
	}

	private void imprimeCartas(ArrayList<Jogador> jogadores, ArrayList<Carta> cartas) {
		if(cartas.size() == 0) return;
		System.out.println("== Cartas na mesa ==");
		for(int i = 0; i < cartas.size(); ++i) {
			System.out.printf("(%s) %s\n", jogadores.get(i), cartas.get(i));
		}
		System.out.println();
	}

	// Executa a rodada com um certo vetor de jogadores (de tamanho qtdJogadores),
	// começando de um índice em particular, retornando o índice do jogador ganhador
	public int executaRodada(ArrayList<Jogador> jogadores, int qtdJogadores, int posInicial) {
		int turno = 0;
		boolean naoPodeTrucar = false;
		ArrayList<Carta> cartasJogadas = new ArrayList<Carta>();

		// Laço de execução da rodada, itera sobre os jogadores existentes
		for(int i = 0; i < qtdJogadores; ++i) {
			imprimeCartas(jogadores, cartasJogadas);
			int posAtual = (posInicial + i) % qtdJogadores;
			int posProximo = (posAtual + 1) % qtdJogadores;
			Jogador atual = jogadores.get(posAtual), proximo;
			if(i == qtdJogadores - 1) naoPodeTrucar = true;

			Carta c;
			Resposta resp = atual.age(naoPodeTrucar);
			switch(resp) {
				case ACEITA:
					c = atual.jogaCarta(turno >= 1);
					cartasJogadas.add(i, c);
					break;
				case AUMENTA:
					proximo = jogadores.get(posProximo);
					ResultadoTruco res = confrontoTruco(atual, proximo);
					switch(res) {
						case ACEITO:
							c = atual.jogaCarta(turno >= 1);
							cartasJogadas.add(i, c);
							break;
						case ATAQUE_CORRE:
							terminaMao = true;
							return posProximo; // a mão encerra com uma derrota para o ataque
						case DEFESA_CORRE:
							terminaMao = true;
							return posAtual; // a mão encerra com uma vitória para o ataque
					}
					break;
				case CORRE:
					terminaMao = true;
					proximo = jogadores.get((posAtual + 1) % qtdJogadores);
					return posProximo; // a mão encerra com uma derrota para o jogador atual
			}
			turno++;
		}
		int indiceMaior = declaraVencedor(cartasJogadas);
		if(indiceMaior == -1) {
			System.out.println("Empate!");
			return -1;
		}

		System.out.printf("O ganhador é o jogador %s!\n\n", jogadores.get(indiceMaior));
		Carta cartaVencedora = cartasJogadas.get(indiceMaior);

		this.cartaVencedora = cartaVencedora;
		return indiceMaior;
	}
}
