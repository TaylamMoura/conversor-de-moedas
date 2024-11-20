package br.com.alura.conversordemoedas.Model;

import java.util.Scanner;

public class Usuario {
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        System.out.println("""
                \nEscolha uma opção para realizar a conversão:
                    1) Real Brasileiro ---> Dólar Americano;
                    2) Real Brasileiro ---> Euro;
                    3) Real Brasileiro ---> Libra Esterlina;
                    4) Real Brasileiro ---> Dólar Canadense;
                    5) Real Brasileiro ---> Iene Japonês;
                    6) Dólar Americano ---> Real Brasileiro;
                    7) Ver HISTÓRICO DE CONVERSÕES;
                    8) SAIR. """);
    }

    public int opcaoDigitada() {
        System.out.println("Digite a opção desejada: ");
        return scanner.nextInt();
    }

    public double valorDigitado() {
        System.out.println("Digite o valor a ser convertido: ");
        return scanner.nextDouble();
    }
}