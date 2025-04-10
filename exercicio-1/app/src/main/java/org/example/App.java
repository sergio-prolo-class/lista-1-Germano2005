/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import java.util.Arrays;
import java.util.Objects;

public class App {

    public static void main(String[] args) {
        int i;
        String forma = " ";
        int dimensoes = 0;
        int alturaRetangulo = 0;

        for (i = 0; i < args.length; i++) { // for para verificar todos os argumentos
            if (Objects.equals(args[i], args[0])) { // conferir arg[0] para ver se é uma forma
                if ("triangulo".equals(args[0]) || "losangulo".equals(args[0]) || "retangulo".equals(args[0])) {
                    forma = args[0];
                    if (forma.equals("retangulo")) {
                        if (args.length < 3){
                            System.out.println("Você escolheu retangulo, e a quantidade de argumentos não corresponde"); // verificar se os argumrntos do retângulo estão de acordo
                            break;
                        }
                        else {
                            if (isNumeric(args[2])) { // conferir a arg[2] para ve se é a dimensão do retangulo
                                alturaRetangulo = Integer.parseInt(args[2]);
                            } else {
                                dimensaoInvalida();
                                break;
                            }
                        }
                    }
                }
                else {
                    dimensaoInvalida();
                    break;
                }
            }
            if (Objects.equals(args[i], args[1])) { // conferir arg[1] para ve se é uma dimensão
                if (isNumeric(args[1])) {
                     dimensoes = Integer.parseInt(args[1]);
                    if (Objects.equals(args[0], "losangulo") && (dimensoes % 2) == 0) {
                        System.out.println("É obrigatório que as dimensões do losangulo sejam ímpares");
                        forma = "erro";
                        break;
                    }
                }
                else {
                    dimensaoInvalida();
                }
            }
        }
        switch (forma){ // switch case para cada tipo de forma
            case "triangulo":
                triangulo(dimensoes);
                break;
            case "losangulo":
                losangulo(dimensoes);
                break;
            case "retangulo":
                retangulo(dimensoes, alturaRetangulo);
                break;
            default:
                dimensaoInvalida();
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  // Verifica inteiros e decimais
    }

    public static void dimensaoInvalida(){
        System.out.println("Digite novamente conforme a ordem: forma/dimensões"); // mensagem de dimensões inválidas
    }

    public static void triangulo(int dimensao){
        char[] triangulo = new char[dimensao];

        for (int i = dimensao; i > 0; i--){ // for responsável por adicionar o * de forma decremental
            triangulo[i - 1] = '*';
            for (int j = 0; j < dimensao; j++){ // for responsável por mostrar na tela os triângulos
                if (triangulo[j] != 0){ // verifica se a posição está vazia ou não
                    System.out.print(triangulo[j]);
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }

    public static void losangulo(int dimensao){
        char[] losangulo = new char[dimensao];
        int meio = dimensao / 2;

        for (int i = 0; i <= meio; i++) { //primeira parte  do losango
            Arrays.fill(losangulo, ' ');

            int esquerda = meio - i; // Calcula as posições onde os asteriscos devem estar
            int direita = meio + i;

            for (int j = esquerda; j <= direita; j++) { // Coloca os asteriscos nas posições calculadas
                losangulo[j] = '*';
            }
            System.out.println(new String(losangulo));
        }

        for (int i = meio - 1; i >= 0; i--) { // segunda parte do losango
            Arrays.fill(losangulo, ' ');

            int esquerda = meio - i; // calcula as posições onde os asteriscos devem estar
            int direita = meio + i;

            for (int j = esquerda; j <= direita; j++) {
                losangulo[j] = '*';
            }

            System.out.println(new String(losangulo));
        }
    }
    public static void retangulo(int dimensao, int altura){
        char[] retangulo = new char[dimensao];

        Arrays.fill(retangulo, '*');// função responsável por adicionar o * em cada posição. Obs: Sugestão da IDE

        for (int i = 0; i < altura; i++){ // for para iterar as linhas
            if (i == 0 || i == altura - 1){ // se a linha for a incial ou final printa tudo
                for (int j = 0; j < dimensao; j++){
                    System.out.print(retangulo[j]);
                }
            }
            else { // se não faz um for nas linahs que não são inicial e final e mostra apenas a primeira e a ultima coluna
                for (int j = 0; j < dimensao; j++){
                    if (j == 0 || j == dimensao - 1){
                        System.out.print(retangulo[j]);
                    }
                    else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.print("\n");
        }
    }
}
