package br.com.alura.conversordemoedas;

import java.util.ArrayList;
import java.util.List;

public class Principal {
    private  static List<RegistroConversao> historico = new ArrayList<>();
    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        int opcao = 0;

        while (opcao != 8) {
            usuario.menu();
            opcao = usuario.opcaoDigitada();

            if (opcao >= 1 && opcao <= 6) {
                double valor = usuario.valorDigitado();
                try {
                    double valorConvertido = ConversorMoeda.converterMoeda(opcao, valor);

                    String valorFormatado = ConversorMoeda.formatarValor(valor);
                    String valorConvertidoFormatado = ConversorMoeda.formatarValor(valorConvertido);
                    String codigoMoedaOrigem = ConversorMoeda.getCodigoMoedaOrigem(opcao);
                    String codigoMoedaDestino = ConversorMoeda.getCodigoMoedaDestino(opcao);


                    System.out.println("\n>>>>>>>>>>>> O valor de "
                            + valorFormatado + " [" + codigoMoedaOrigem + "] equivale a "
                            + valorConvertidoFormatado + " [" + codigoMoedaDestino + "]\n");
                    adicionarHistorico(codigoMoedaOrigem, codigoMoedaDestino, valor, valorConvertido);
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            } else if (opcao == 7) {
                exibirHistorico();
            } else if(opcao == 8) {
                System.out.println("Obrigada pela preferência! Volte Sempre!");
            } else {
                System.out.println("Opção Inválida. Digite novamente!");
            }
        }
    }
    private static void adicionarHistorico(String moedaOrigem, String moedaDestino, double valorOriginal, double valorConvertido) {
        RegistroConversao registro = new RegistroConversao(moedaOrigem, moedaDestino, valorOriginal, valorConvertido);
        historico.add(registro);
    }

    private static void exibirHistorico() {
        System.out.println("\nHistórico de Conversões:");
        if (historico.isEmpty()) {
            System.out.println("Nenhuma conversão realizada.");
        } else {
            int contador = 1;
            for (RegistroConversao registro : historico) {
                System.out.println(contador +"ª " +registro);
                contador++;
        }
    }
}

}

