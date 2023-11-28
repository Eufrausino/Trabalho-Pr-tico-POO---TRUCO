package br.ufv.truco;

public class Carta {

    private int valor;
    private Naipe naipe;
    private boolean encoberta;

    public Carta(int valor, Naipe naipe) {
        if(valor < 1 || valor > 10)
            throw new ExceptionInInitializerError("Valor inválido! " + valor);
        this.valor = valor;
        this.naipe = naipe;
        encoberta = false;
    }

    @Override
    public String toString() {
        char naipeTexto;
        String valorTexto;
        switch(valor) {
            case 1:
                valorTexto = "A";
                break;
            case 8:
                valorTexto = "Q";
                break;
            case 9:
                valorTexto = "J";
                break;
            case 10:
                valorTexto = "K";
                break;
            default:
                valorTexto = Integer.toString(valor);
                break;
        }
        naipeTexto = naipe.name().charAt(0);
        return valorTexto + "-" + naipeTexto;
    }

    public boolean estaEncoberta() {
        return encoberta;
    }

    public void setEncoberta(boolean encoberta) {
        this.encoberta = encoberta;
    }

    public int compara(Carta c) {
        if(this.naipe == c.naipe && this.valor == c.valor)
            return 0;

        // O Zap (4 de paus) é maior que todas as outras cartas
        if(this.naipe == Naipe.PAUS && this.valor == 4)
            return 1;
        if(c.naipe == Naipe.PAUS && c.valor == 4)
            return -1;

        // Abaixo dele, o maior é o 7 de copas
        if(this.naipe == Naipe.COPAS && this.valor == 7)
            return 1;
        if(c.naipe == Naipe.COPAS && c.valor == 7)
            return -1;

        // Abaixo dele, o maior é a espadilha (ás de espadas)
        if(this.naipe == Naipe.ESPADAS && this.valor == 1)
            return 1;
        if(c.naipe == Naipe.ESPADAS && c.valor == 1)
            return -1;

        // Abaixo dele, o maior é o 7 de ouros
        if(this.naipe == Naipe.OUROS && this.valor == 7)
            return 1;
        if(c.naipe == Naipe.OUROS && c.valor == 7)
            return -1;

        // Se o valor das cartas estiver entre 1 e 3, ganha o maior número
        if(this.valor >= 1 && this.valor <= 3 && c.valor >= 1 && c.valor <= 3)
            return this.valor > c.valor ? 1 : -1;

        // Abaixo disso, se o valor das cartas estiver entre 4 e 10, o mesmo acontece
        if(this.valor >= 4 && this.valor <= 10 && c.valor >= 4 && c.valor <= 10)
            return this.valor > c.valor ? 1 : -1;

        return 0; // não deveria acontecer
    }

    public boolean ganhaDe(Carta c) {
        return this.compara(c) > 0;
    }
}
