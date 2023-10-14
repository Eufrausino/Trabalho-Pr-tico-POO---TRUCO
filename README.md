# Trabalho-Pratico-POO---TRUCO
Trabalho prático da disciplina CCF 313 - Programação Orientada a Objetos
Abaixo estará listado as regras do jogo e questão: 

MANUAL TRUCO

O truco é um jogo de cartas com regras, gritos, mas o mais importante de suas características é a estratégia. É jogado exclusivamente por 4 pessoas, que dividem-se em duplas. 

No truco, as cartas são ordenadas da seguinte forma, da mais forte à mais fraca: 4 de paus, 7 de copas, A de espadas, 7 de ouros, as 4 cartas mais fortes, as únicas em que o naipe importa. Em seguida, as cartas em que o naipe não importa: qualquer 3, 2, A de paus, copas e ouros, qualquer K, J, Q, 7 de paus e espadas, qualquer 6, 5, e 4 de copas, espadas e ouros. 

Com a ordem das cartas definidas, agora uma explicação de como funciona o jogo.

No truco, há rodadas, mãos e jogos. O objetivo é vencer 2 de 3 jogos. Para vencer um jogo, é necessário vencer 12 pontos em um número x de mãos, em que cada jogador da mesa recebe 3 cartas. Esse número x depende de quanto vale cada mão, que será explicado em breve. Cada mão começa com um jogador embaralhando, em seguida, o jogador a sua esquerda corta/divide o baralho em dois, entrega uma das partes ao oponente que embaralhou e informa como este deve distribuir as cartas: de baixo pra cima(subir) ou de cima pra baixo(descer). As mãos consistem de 3 rodadas e, em cada mão, o jogador à direita do que embaralhou começa jogando uma carta, em seguida o próximo em sentido horário até chegar ao que embaralhou primeiramente. Vence a rodada quem tiver jogado a maior carta, e começa a próximo rodada quem tiver vencido a última. Vence a mão quem vencer 2 rodadas. Ao fim da mão, o jogador que começou essa embaralha na próxima. As mãos inicialmente valem 2 pontos, entretanto, essa quantidade de pontos pode ser alterada. Quando for sua vez de jogar, um jogador pode propor truco, ou seja, propor que a mão valha 4 pontos. Neste momento, o oponente tem 3 opções: negar e perder 2 pontos, aceitar e continuar jogando valendo 4 ou propor seis, em que a mão vale 8 pontos (sim, pedir seis faz valer 8). Isso pode seguir-se algumas vezes, após o seis, pode-se pedir nove, que vale 10 pontos, e doze, que vale 12 pontos. Há, entretanto, uma mão especial: a mão de 10. Essa mão ocorre quando uma dupla de jogadores possui 10 pontos. Nessa mão ninguém pode propor truco e os jogadores podem decidir se jogam ou não. Se não jogarem, os oponentes ganham 2 pontos, se jogarem e perderem, os oponentes ganham 4 pontos, e se jogarem e vencerem, eles ganham 2 pontos e vencem o jogo. Se alguém pedir truco, a mão acaba e o time oponente ganha 2 pontos. 

Regras adicionais/opcionais:

Não pode cortar gaveta na mão de 10:: Se for uma mão de 10, quando o jogador à esquerda do que embaralhou for cortar, ele não pode entregar um conjunto de cartas que estão no meio do baralho, ou seja, que tenham pelo menos uma carta acima e uma carta a baixo. O conjunto deve ter uma das cartas na extremidade.

Pode descartar menor que 7: Se, ao receber as 3 cartas, o jogador perceber que todas as 3 são menores ou iguais a um 7 preto, ele pode descartá-las e pedir mais 3

Pode descartar família real: Se, ao receber as 3 cartas, o jogador perceber que todas as 3 exatamente um K, um J e um Q, ele pode descartá-las e pedir mais 3.

Mão de Ferro: Se, em uma mão, ambas as duplas tiverem 10 pontos, os jogadores jogam sem ver as próprias cartas. 

3 e meio: Adiciona o coringa como uma carta entre os 3 e o 7 de ouros na sequência de cartas. 

Mata zap: O 4 de espadas vence apenas do 4 de paus, porém não vence do 7 de copas, A de espadas ou nenhuma outra carta.

Desta maneira, faremos a simulação de uma partida de truco, exibindo as cartas e realizando as jogadas feitas pelos os usuários. Além disso, adicionaremos uma interface gráfica ao jogo.
