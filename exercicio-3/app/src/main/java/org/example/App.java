/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import java.util.Random;

public class App {
    public static void main(String[] args) {
        char[][] tabuleiro = {{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.',},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.',},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.',},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.',},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.',},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.',},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.',},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.',},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.',},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.',},}; //Matriz

        Random numAleatorio1 = new Random();
        Random numAleatorio2 = new Random();

        int linha = numAleatorio1.nextInt(9);
        int coluna = numAleatorio2.nextInt(9);

        System.out.println(linha);
        System.out.println(coluna);

        //Porta avião
        for (int i = 0; i < 5; i++){
            if (coluna >= 6){
                System.out.print("Não deu para colocar");
                break;
            }
            tabuleiro[linha][coluna + i] = 'P';
        }

        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                System.out.print(tabuleiro[i][j]);
            }
            System.out.print("\n");
        }

    }
}
