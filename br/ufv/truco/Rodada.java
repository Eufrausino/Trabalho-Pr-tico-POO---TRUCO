package br.ufv.truco;
import java.util.ArrayList;

public class Rodada
{
	private Equipe equipeVencedora;
	private int trucado = 0;
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

	public int declaraVencedor(ArrayList<Carta> cartas)
	{
		Carta maior = cartas.get(0);

		for(int i = 1; i < cartas.size(); i++)
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
		ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
		jogadores.add(equipe1.getJogador1());
		jogadores.add(equipe1.getJogador2());
		jogadores.add(equipe2.getJogador1());
		jogadores.add(equipe2.getJogador2());

		// Distribui as cartas para cada um dos jogadores
		for(Jogador j : jogadores) {
			for(int i = 0; i < 3; ++i)
				if(j != null)
					j.recebeCarta(baralho.retiraCartaAleatoria());
		}

		int turno = 0;
		int prox = 1;
		int atual = prox - 1;

		ArrayList<Carta> cartasJogadas = new ArrayList<Carta>();

		for(Jogador j : jogadores)
		{
			Carta c;
			Resposta resp = j.age();

			if(prox < 4)
			{
				switch(resp)
				{
					case ACEITA:
						c = j.jogaCarta(turno >= 1);
						cartasJogadas.add(c);
						break;
					case AUMENTA:
						ResultadoTruco res = confrontoTruco(jogadores.get(atual), jogadores.get(prox));
						switch(res)
						{
							case ACEITO:
								c = j.jogaCarta(turno >= 1);
								cartasJogadas.add(c);
								break;
							case ATAQUE_CORRE:
								definirVencedor(null, jogadores.get(prox), equipe1, equipe2);
								return; // rodada encerra
							case DEFESA_CORRE:
								definirVencedor(null, jogadores.get(atual), equipe1, equipe2);
								return; // rodada encerra
						}
						break;
					case CORRE:
						definirVencedor(null, jogadores.get(prox), equipe1, equipe2);
						return; // rodada encerra
				}
			}

			else if(prox >= 4)
			{
				switch(resp)
				{
					case ACEITA:
						c = j.jogaCarta(turno >= 1);
						cartasJogadas.add(c);
						break;
					case CORRE:
						definirVencedor(null, jogadores.get(prox), equipe1, equipe2);
						return; // rodada encerra
					default:
						break;
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
