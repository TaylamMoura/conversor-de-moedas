package br.com.alura.conversordemoedas.Model;

public class RegistroConversao {
    private String moedaOrigem;
    private String moedaDestino;
    private double valorOriginal;
    private double valorConvertido;

    public RegistroConversao(String moedaOrigem, String moedaDestino, double valorOriginal, double valorConvertido) {
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.valorOriginal = valorOriginal;
        this.valorConvertido = valorConvertido;
    }

    @Override
    public String toString() {
        return "Conversão de [" + moedaOrigem + "] " + valorOriginal +
                " para [" + moedaDestino + "] " + valorConvertido;
    }
}
