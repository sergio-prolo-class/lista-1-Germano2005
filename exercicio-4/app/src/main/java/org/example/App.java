package org.example;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] tabuleiro = new char[10][10]; //tabuleiro
        int contLinhas = 0;//contador de linhas


        while (scanner.hasNextLine() && contLinhas < 10) { //itera o tabuleiro lido por redirecionamento de entrada
            String linha = scanner.nextLine().trim();
            if (linha.length() != 10) {
                System.out.println("Tabuleiro inválido: não tem a dimensão correta de 10×10 casas");
                scanner.close();
                return;
            }
            tabuleiro[contLinhas] = linha.toCharArray();
            contLinhas++; //conta carctere por caractere
        }


        if (contLinhas != 10) { // verificar se tem 10 linhas
            System.out.println("Tabuleiro inválido: não tem a dimensão correta de 10×10 casas");
            scanner.close();
            return;
        }


        char[] tiposNavios = {'P', 'T', 'C', 'E', 'S'}; // tipos de navios
        int[] tamanhosNavios = {5, 4, 3, 2, 1}; //tamanhos


        String[] mensagens = new String[4]; // mensagens

        mensagens[0] = verificaNaviosDesconhecidos(tabuleiro, tiposNavios);
        mensagens[1] = verificaNaviosPresentes(tabuleiro, tiposNavios);
        mensagens[2] = verificaNaviosRepetidos(tabuleiro, tiposNavios, tamanhosNavios);
        mensagens[3] = verificaOrientacaoNavios(tabuleiro, tiposNavios, tamanhosNavios);


        boolean tabuleiroValido = true;
        for (String mensagem : mensagens) {
            if (mensagem != null) { // verificador de erros
                System.out.println("Tabuleiro inválido: " + mensagem);
                tabuleiroValido = false;
            }
        }

        if (tabuleiroValido) {
            System.out.println("Tabuleiro válido!");
        }

        scanner.close();
    }


    public static String verificaNaviosDesconhecidos(char[][] tabuleiro, char[] tiposNavios) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                char c = tabuleiro[i][j];
                if (c != '.') { // verifica cada posição do navio para ve se é desconhecido
                    boolean navioConhecido = false;
                    for (char tipo : tiposNavios) { // foreach para verificar cada tipo de navio
                        if (c == tipo) {
                            navioConhecido = true;
                            break;
                        }
                    }
                    if (!navioConhecido) {
                        return "inclui navios desconhecidos";
                    }
                }
            }
        }
        return null;
    }

    public static String verificaNaviosPresentes(char[][] tabuleiro, char[] tiposNavios) {
        for (char tipo : tiposNavios) {
            boolean encontrado = false;
            for (int i = 0; i < 10 && !encontrado; i++) {
                for (int j = 0; j < 10 && !encontrado; j++) {
                    if (tabuleiro[i][j] == tipo) { // verifica se o caracter do navio correspondente foi encontrado
                        encontrado = true;
                    }
                }
            }
            if (!encontrado) {
                return "não inclui um navio de cada tipo";
            }
        }
        return null;
    }

    public static String verificaNaviosRepetidos(char[][] tabuleiro, char[] tiposNavios, int[] tamanhosNavios) {
        for (int k = 0; k < tiposNavios.length; k++) { // verifica se a um tipo desconhecido de navio com o tamaho deles
            int contador = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (tabuleiro[i][j] == tiposNavios[k]) {
                        contador++;
                    }
                }
            }

            if (contador != tamanhosNavios[k]) {
                return "inclui múltiplos navios do mesmo tipo ou tamanho incorreto";
            }
        }
        return null;
    }

    public static String verificaOrientacaoNavios(char[][] tabuleiro, char[] tiposNavios, int[] tamanhosNavios) {
        boolean[][] visitado = new boolean[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tabuleiro[i][j] != '.' && !visitado[i][j]) {
                    char tipo = tabuleiro[i][j];
                    int tamanhoEsperado = tamanhoNavio(tipo, tiposNavios, tamanhosNavios); // obtem o tamaho do navio


                    int tamanhoHorizontal = 1; // Verificar orientação horizontal
                    if (j < 9 && tabuleiro[i][j+1] == tipo) {
                        int x = j + 1;
                        while (x < 10 && tabuleiro[i][x] == tipo) {
                            if (visitado[i][x]) {
                                return "navios sobrepostos ou em posição inválida";
                            }
                            visitado[i][x] = true;
                            tamanhoHorizontal++;
                            x++;
                        }

                        if (tamanhoHorizontal != tamanhoEsperado) {
                            return "navio " + tipo + " com tamanho incorreto";
                        }
                    }

                    else if (i < 9 && tabuleiro[i+1][j] == tipo) { // Verificar orientação vertical
                        int y = i + 1;
                        int tamanhoVertical = 1;
                        while (y < 10 && tabuleiro[y][j] == tipo) {
                            if (visitado[y][j]) {
                                return "navios sobrepostos ou em posição inválida";
                            }
                            visitado[y][j] = true;
                            tamanhoVertical++;
                            y++;
                        }

                        if (tamanhoVertical != tamanhoEsperado) {
                            return "navio " + tipo + " com tamanho incorreto";
                        }
                    }

                    else if (tamanhoEsperado == 1) { // Verificar submarino (tamanho 1)
                        visitado[i][j] = true;
                    }
                    else {
                        return "navio " + tipo + " não está na horizontal ou vertical";
                    }
                }
            }
        }
        return null;
    }

    public static int tamanhoNavio(char tipo, char[] tiposNavios, int[] tamanhosNavios) {
        for (int i = 0; i < tiposNavios.length; i++) {
            if (tiposNavios[i] == tipo) {
                return tamanhosNavios[i];
            }
        }
        return 0;
    }
}