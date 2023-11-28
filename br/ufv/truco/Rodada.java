package br.ufv.truco;
import java.util.Scanner;
import java.util.ArrayList;

public class Rodada
{
	private Equipe equipeVencedora;
	private int trucado = 0;
	private Scanner leitor;
	
	public Rodada()
	{
		
	}
	
	public Rodada(Equipe EquipeVencedora, int Trucado)
	{
		this.equipeVencedora = EquipeVencedora;
		this.trucado = Trucado;
	}
	
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
	
	public void trucar(Jogador alvo)//Certo
	{
		System.out.println("Truco!");
		trucado++;
	}

	public char resposta(Jogador alvo)
	{
		this.leitor = new Scanner(System.in);
		System.out.println("Corre, aceita ou pede mais?");
		System.out.println("Entre com 0 para correr, 1 para aceitar ou 2 para trucar: ");
		int resposta = leitor.nextInt();
		
		switch(resposta)
		{
			case 0:
				System.out.println("Correu!");
				return 'C';
			case 1:
				System.out.println("Aceitou!");
				return 'A';
			case 2:
				switch(this.trucado)
				{
					case 1:
						System.out.println("SEEEEIS!");
						this.trucar(alvo);
						return 'S';
					case 2:
						System.out.println("NOOOOOVE!");
						this.trucar(alvo);
						return 'N';
					case 3:
						System.out.println("DOOOZE!");
						return 'D';
					default:
						break;
				}
				break;
			default:
				break;
		}
		leitor.close();
		return 'E';//erro
	}

	public Carta jogar(Jogador jogador, int turno)
	{
		boolean coberta = false;

		if(turno >= 1)
		{
			System.out.println("Jogar encoberta? Entre com 0 para não OU 1 para sim");
			int acobertada = leitor.nextInt();

			switch (acobertada) 
			{
				case 0:
					coberta = false;
					break;
			
				case 1:
					coberta = true;
					break;
				default:
					break;
			}

			return jogador.jogarCarta(coberta);
		}

		return jogador.jogarCarta(coberta);
	}

	public int ConfrontoTruco(Jogador j1, Jogador j2)
	{
		this.resposta(j2);

		switch(this.trucado)
		{
			case 1:
				this.resposta(j1);
				switch (this.resposta(j1)) 
				{
					case 'C':
						return 0;

					case 'S':
						this.resposta(j2);
						switch (this.resposta(j2))
						{
							case 'C':
								return 0;
							
							case 'N':
								this.resposta(j1);
								switch (this.resposta(j1)) 
								{
									case 'C':
										return 0;

									case 'D':
										switch (this.resposta(j2))
										{
											case 'C':
												return 0;
											default:
												return 1;
										}
								
									default:
										return 1;
								}
							default:
								return 1;
						}
						default:
							return 1;
					}
			case 2:
			this.resposta(j1);
			switch (this.resposta(j1))
			{
				case 'C':
					return 0;
				
				case 'N':
					this.resposta(j2);
					switch (this.resposta(j1)) 
					{
						case 'C':
							return 0;

						case 'D':
							switch (this.resposta(j2))
							{
								case 'C':
									return 0;
								default:
									return 1;
							}
					
						default:
							return 1;
					}
				
					default:
						return 1;
				}

				case 3:
				this.resposta(j1);
				switch (this.resposta(j1)) {
					case 'C':
						return 0;
					case 'D':
						this.resposta(j2);
						switch (this.resposta(j2)) 
						{
							case 'C':
								return 0;
						
							default:
								return 1;
						}
					default:
						return 1;
				}
			
				default:
					return 1;
		}
	}

	public void declaraVencedor(Carta c1, Carta c2, Carta c3, Carta c4)
	{
		ArrayList<Carta> cartasNaMesa = new ArrayList<Carta>();

		cartasNaMesa.add(c1);
		cartasNaMesa.add(c2);
		cartasNaMesa.add(c3);
		cartasNaMesa.add(c4);

		Carta maior = cartasNaMesa.get(0);
		
		for(int i = 1; i <= cartasNaMesa.size(); i++)
		{
			if(!maior.ganhaDe(cartasNaMesa.get(i)))
			{
				maior = cartasNaMesa.get(i);
			}
		}

		//TODO: Falta associar a carta ao jogador que a jogou

	}
	
	public void executaRodada(Equipe equipe1, Equipe equipe2)
	{
		this.leitor = new Scanner(System.in);
		
		ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
		jogadores.add(equipe1.getJogador1());
		jogadores.add(equipe1.getJogador2());
		jogadores.add(equipe2.getJogador1());
		jogadores.add(equipe2.getJogador2());

		int turno = 0;
		int atual = 1;
		int anterior = atual - 1;

		boolean correu = false;
		
		ArrayList<Carta> cartasJogadas = new ArrayList<Carta>();
		
		for(Jogador j : jogadores)
		{
			System.out.println("Jogar ou pedir truco?");
			System.out.println("Entre com 0 para jogar ou 1 para pedir truco: ");
			int resposta = leitor.nextInt();

			switch(resposta)
			{
				case 0:
					this.jogar(j, turno);
					cartasJogadas.add(this.jogar(j, turno));
					break;
				case 1:
					this.ConfrontoTruco(jogadores.get(atual), jogadores.get(anterior));
					switch(this.ConfrontoTruco(jogadores.get(atual), jogadores.get(anterior)))
					{
						case 0:
							System.out.println("Correu!");
							correu = true;
							break;
						case 1:
							System.out.println("Aceitou!");
							this.jogar(j, turno);
							cartasJogadas.add(this.jogar(j, turno));
							break;
						default:
							break;
					}
				default:
					break;
			}

			if(correu == true)
			{
				break;
			}

			turno++;
			anterior = atual;
			atual++;
		}

		//TODO: encontrar o vencedor da rodada. Carta -> Jogador -> Equipe
	}

	/*public Jogador definirVencedor()
	{
		//Comparar a maior carta da mesa -> associar ela ao jogador que a jogou -> associar o
		//jogador a equipe;
		//Iniciar nova rodada a partir desse jogador;
		
	}*/
}
