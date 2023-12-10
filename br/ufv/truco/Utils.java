package br.ufv.truco;

import java.io.IOException;
import java.util.Scanner;

// Conjunto de funções utilitárias para o resto do programa
public class Utils {
    public static Scanner scan = new Scanner(System.in);

    // Pede ao usuário por confirmação para limpar a tela e passar para o
    // próximo jogador no jogo
    public static void passarProProximo() {
        System.out.print("\nAperte ENTER para passar para o próximo jogador... ");
        while(!scan.nextLine().isEmpty());

        try {
            if(System.getProperty("os.name").contains("Window"))
                // O CMD do Windows não suporta sequências de escape de terminal
                // Assim, temos que utilizar o comando cls do CMD
                // Créditos para a linha seguinte:
                // https://stackoverflow.com/questions/2979383
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                // Outros sistemas, como o terminal do Linux, tem esse suporte
                System.out.print("\033\143");
        } catch(IOException | InterruptedException ex) {}
    }
}
