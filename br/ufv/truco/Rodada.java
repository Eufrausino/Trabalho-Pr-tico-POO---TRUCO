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
	
	public void trucar(Jogador alvo)
	{
		System.out.println("Truco!");
		trucado++;
	}
	
	public char resposta(Jogador alvo)
	{
		//Como o retorno original era um char, mantive assim
		//S = seis, N = nove e D = doze
		
		this.leitor = new Scanner(System.in);
		System.out.println("Corre, aceita ou pede mais?");
		System.out.println("Entre com 0 para correr, 1 para aceitar ou 2 para trucar: ");
		int resposta = leitor.nextInt();
		
		switch(resposta)
		{
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
		
		System.out.println("Correu!");
		return 'C';
	}
	
	public void executaRodada(Equipe equipe1, Equipe equipe2)
	{
		this.leitor = new Scanner(System.in);
		int cont = 0;
		
		ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
		jogadores.add(equipe1.getJogador1);
		jogadores.add(equipe1.getJogador2);
		jogadores.add(equipe2.getJogador1);
		jogadores.add(equipe2.getJogador2);
		
		ArrayList<Carta> cartasJogadas = new ArrayList<Carta>();
		
		for(Jogador j : jogadores)
		{
			System.out.println("Jogar ou pedir truco?");
			System.out.println("Entre com 0 para jogar ou 1 para pedir truco: ");
			int resposta = leitor.nextInt();
			boolean coberta = false;
			cont++;
			
			switch(resposta)
			{
				case 0:
					System.out.println("Jogar carta encoberta ou não?");
					System.out.println("0 para não/1 para sim: ");
					int jogarEncoberta = leitor.nextInt();
					switch(jogarEncoberta)
					{
						case 0:
							coberta = false;
							j.jogarCarta(coberta);
							cartasJogadas.add(j.jogarCarta(coberta));
							break;
						case 1:
							coberta = true;
							j.jogarCarta(coberta);
							cartasJogadas.add(j.jogarCarta(coberta));
							break;
						default:
							break;
					}
				case 1:
					//j.pedirTruco();
					this.trucar(jogadores.get(cont));
					while(true)
					{
						this.resposta(j);
						if(this.resposta(j) == 'C')
						{
							//perde pts;
							return;
						}
						
						else if(this.resposta(j) == 'A')
						{
							//jogo continua normalmente
							break;
						}
						
						else if(this.resposta(j) == 'S')
						{
							this.resposta(jogadores.get(cont));
							switch(this.resposta(jogadores.get(cont)))
							{
								case 'C':
									//perde pts
									return;
								case 'N':
									this.resposta(j);
									switch(this.resposta(jogadores.get(cont)))
									{
										case 'C':
											//perde pts
											return;
										case 'D':
											this.resposta(j);
											switch(this.resposta(j))
											{
												case 'C':
													//perde pts
													return;
												default:
													break;
											}
											break;
										default:
											break;
									}
									break;
								default:
									break;
							}
							break;
						}
						
						else if(this.resposta(j) == 'N')
						{
							this.resposta(jogadores.get(cont));
							switch(this.resposta(jogadores.get(cont)))
							{
								case 'C':
									//perde pts
									return;
								case 'D':
									this.resposta(j);
									switch(this.resposta(j))
									{
										case 'C':
											return;
										default:
											break;
									}
									break;
								default:
									break;
							}
							break;
						}
						
						else if(this.resposta(j) == 'D')
						{
							this.resposta(jogadores.get(cont));
							switch(this.resposta(jogadores.get(cont)))
							{
								case 'C':
									//perde pts
									return;
								default:
									break;
							}
							break;
						}
					}
					System.out.println("Jogar carta encoberta ou não?");
					System.out.println("0 para não/1 para sim: ");
					int JogarEncoberta = leitor.nextInt();
					switch(JogarEncoberta)
					{
						case 0:
							coberta = false;
							j.jogarCarta(coberta);
							cartasJogadas.add(j.jogarCarta(coberta));
							break;
						case 1:
							coberta = true;
							j.jogarCarta(coberta);
							cartasJogadas.add(j.jogarCarta(coberta));
							break;
						default:
							break;
					}	
			}
		}
		
		Carta maior = cartasJogadas.get(0);
		for(int i = 1; i <= cartasJogadas.size(); i++)
		{
			if(maior.compara(maior) < cartasJogadas.get(i).compara(cartasJogadas.get(i)))
			{
				maior = cartasJogadas.get(i);
			}
		}
		//Associar jogador com a carta;
		//Depois que descobrimos qual jogador que jogou a carta, saberemos a qual equipe ele pertence
	}
	
	public Jogador definirVencedor()
	{
		//Comparar a maior carta da mesa -> associar ela ao jogador que a jogou -> associar o
		//jogador a equipe;
		//Iniciar nova rodada a partir desse jogador;
		
	}
}
