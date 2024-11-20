package br.com.alura.conversordemoedas;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class ConversorMoeda {
    private static final Map<Integer, String> CODIGO = new HashMap<>();
    static {
        CODIGO.put(1, "BRL-USD");
        CODIGO.put(2, "BRL-EUR");
        CODIGO.put(3, "BRL-GBP");
        CODIGO.put(4, "BRL-CAD");
        CODIGO.put(5, "BRL-JPY");
        CODIGO.put(6, "USD-BRL");
    }
    public static double converterMoeda(int opcao, double valor) throws Exception {
        String[] codigoDaMoeda = CODIGO.get(opcao).split("-");
        String codigoMoedaOrigem = codigoDaMoeda[0];
        String codigoMoedaDestino = codigoDaMoeda[1];
        double taxaConversao = ApiConexao.obterTaxaConversao(codigoMoedaOrigem, codigoMoedaDestino);
        return valor * taxaConversao;
    }

    public static String formatarValor(double valor) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(valor);
    }

    public static String getCodigoMoedaOrigem(int opcao) {
        return CODIGO.get(opcao).split("-")[0];
    }

    public static String getCodigoMoedaDestino(int opcao) {
        return CODIGO.get(opcao).split("-")[1];
    }
}
