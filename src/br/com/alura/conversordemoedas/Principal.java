package br.com.alura.conversordemoedas;
import  br.com.alura.conversordemoedas.ApiConexao;

public class Principal {
    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        int opcao = 0;

        while (opcao != 7) {
            usuario.menu();
            opcao = usuario.opcaoDigitada();

            if (opcao >= 1 && opcao <= 6) {
                double valor = usuario.valorDigitado();
                ApiConexao.conectarNaApi(opcao, valor);
            } else if (opcao == 7) {
                System.out.println("Obrigada pela preferência! Volte Sempre!");
            } else {
                System.out.println("Opção Inválida. Digite novamente!");
            }
        }
    }
}
