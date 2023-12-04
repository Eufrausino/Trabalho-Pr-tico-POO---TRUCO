package br.ufv.truco;
import java.util.Scanner;
import java.util.ArrayList;

public class Rodada
{
	private Equipe equipeVencedora;
	private int trucado = 0;
	private Scanner leitor;
	public Jogador jogadorVencedor;
	private Baralho baralho = new Baralho();

	public Rodada() {}

	public void setEquipeVencedora(Equipe EquipeVencedora)
	{
		this.equipeVencedora = EquipeVencedora;
	}

	public Equipe getEquipeVencedora()
	{
		return this.equipeVencedora;
	}

	public void setTrucado(int Trucado)
	{
		this.trucado = Trucado;
	}

	public int getTrucado()
	{
		return trucado;
	}

	public Carta jogar(Jogador jogador, int turno)
	{
		boolean coberta = false;

		if(turno >= 1)
		{
			System.out.println("Jogar encoberta? Entre com 0 para não OU 1 para sim");
			int acobertada = leitor.nextInt();
			coberta = (acobertada == 1);
		}

		return jogador.jogarCarta(coberta);
	}

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

	public ResultadoTruco confrontoTruco(Jogador ataque, Jogador defesa)
	{
		if(!trucar()) return ResultadoTruco.ACEITO;
		while(true) {
			// Resposta da defesa
			while(true) {
				Resposta def = defesa.resposta();
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
				Resposta atq = ataque.resposta();
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

	public int declaraVencedor(ArrayList<Carta> cartas)
	{
		Carta maior = cartas.get(0);

		for(int i = 1; i <= cartas.size(); i++)
		{
			if(!maior.ganhaDe(cartas.get(i)))
			{
				maior = cartas.get(i);
			}
		}

		return cartas.indexOf(maior);
	}

	public void executaRodada(Equipe equipe1, Equipe equipe2)
	{
		this.leitor = new Scanner(System.in);

		ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
		jogadores.add(equipe1.getJogador1());
		jogadores.add(equipe1.getJogador2());
		jogadores.add(equipe2.getJogador1());
		jogadores.add(equipe2.getJogador2());

		// Distribui as cartas para cada um dos jogadores
		for(Jogador j : jogadores) {
			for(int i = 0; i < 3; ++i)
				if(j != null)
					j.receberCartas(baralho.retiraCartaAleatoria());
		}

		int turno = 0;
		int prox = 1;
		int atual = prox - 1;

		ArrayList<Carta> cartasJogadas = new ArrayList<Carta>();

		for(Jogador j : jogadores)
		{
			int resposta = 0;
			if(trucado < 4) {
				while(true) {
					System.out.println("Jogar ou pedir truco?");
					System.out.println("Entre com 0 para jogar ou 1 para pedir truco: ");
					resposta = leitor.nextInt();
					if(resposta == 0 || resposta == 1) break;
					// Resposta inválida!
					System.err.println("[!] Resposta inválida, tente novamente");
				}
			}

			if(resposta == 0)
			{
				cartasJogadas.add(this.jogar(j, turno));
			}
			else
			{
				ResultadoTruco res = confrontoTruco(jogadores.get(prox), jogadores.get(atual));
				switch(res)
				{
					case ACEITO:
						this.jogar(j, turno);
						cartasJogadas.add(this.jogar(j, turno));
						break;
					case ATAQUE_CORRE:
						definirVencedor(null, jogadores.get(prox), equipe1, equipe2);
						return; // rodada encerra
					case DEFESA_CORRE:
						definirVencedor(null, jogadores.get(atual), equipe1, equipe2);
						return; // rodada encerra
				}
			}
			turno++;
			atual = prox;
			prox++;
		}

		int indiceMaior = this.declaraVencedor(cartasJogadas);
		Carta cartaVencedora = cartasJogadas.get(indiceMaior);
		jogadorVencedor = jogadores.get(indiceMaior);

		definirVencedor(cartaVencedora, jogadorVencedor, equipe1, equipe2);
	}

	public void definirVencedor(Carta CartaVencedora, Jogador jogadorVencedor, Equipe e1, Equipe e2)
	{
		if(jogadorVencedor.equals(e1.getJogador1()) || jogadorVencedor.equals(e1.getJogador2()))
		{
			this.equipeVencedora = e1;
		}

		if(jogadorVencedor.equals(e2.getJogador1()) || jogadorVencedor.equals(e2.getJogador2()))
		{
			this.equipeVencedora = e2;
		}
	}

}
