package br.ufv.truco;
import java.util.ArrayList;

public class Rodada
{
	private int trucado = 0;
	private Jogador jogadorVencedor;
	private Carta cartaVencedora;

	public int getTrucado() {
		return trucado;
	}

	public void setTrucado(int trucado) {
		this.trucado = trucado;
	}

	public Jogador getVencedor() {
		return jogadorVencedor;
	}

	public Carta getCartaVencedora() {
		return cartaVencedora;
	}

	private void definirVencedor(Jogador jogadorVencedor, Carta cartaVencedora) {
		this.jogadorVencedor = jogadorVencedor;
		this.cartaVencedora = cartaVencedora;
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
		return indiceMaior;
	}

	// Executa a rodada com um certo vetor de jogadores (de tamanho qtdJogadores),
	// começando de um índice em particular, retornando o índice do jogador ganhador
	public int executaRodada(ArrayList<Jogador> jogadores, int qtdJogadores, int posInicial) {
		int turno = 0;
		boolean naoPodeTrucar = false;
		ArrayList<Carta> cartasJogadas = new ArrayList<Carta>();

		// Laço de execução da rodada, itera sobre os jogadores existentes
		for(int i = 0; i < qtdJogadores; ++i) {
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
							definirVencedor(proximo, null);
							return posProximo; // rodada encerra
						case DEFESA_CORRE:
							definirVencedor(atual, null);
							return posAtual; // rodada encerra
					}
					break;
				case CORRE:
					proximo = jogadores.get((posAtual + 1) % qtdJogadores);
					definirVencedor(proximo, null);
					return posProximo; // rodada encerra
			}
			turno++;
		}
		int indiceMaior = declaraVencedor(cartasJogadas);
		Carta cartaVencedora = cartasJogadas.get(indiceMaior);
		jogadorVencedor = jogadores.get(indiceMaior);

		definirVencedor(jogadorVencedor, cartaVencedora);
		return indiceMaior;
	}
}
