package br.com.alura.conversordemoedas;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;

public class ApiConexao {
    private static final String API_KEY = "083a98ef10eaba9674b5394f"; //STATIC=existirá em todas instâncias da classe. FINAL= define que o valor não pode ser alterado.

    private static final String[] CODIGO = {
            "BRL-USD",
            "BRL-EUR",
            "BRL-GBP",
            "BRL-CAD",
            "BRL-JPY",
            "USD-BRL"
    };

    public static void conectarNaApi(int opcao, double valor) {



        try {
            String[] codigoDaMoeda = CODIGO[opcao - 1].split("-");
            String codigoMoedaOrigem = codigoDaMoeda[0];
            String codigoMoedaDestino = codigoDaMoeda[1];

            //https://v6.exchangerate-api.com/v6/ SUA-CHAVE-API / par/ EUR / GBP
            String endereco = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + codigoMoedaOrigem + "/" + codigoMoedaDestino;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco)).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() ==200) {
                String json = response.body();
                System.out.println(json);

                Gson gson = new Gson();
                Conversao conversao = gson.fromJson(json, Conversao.class);

                //CALCULO DO VALOR CONVERTIDO
                double taxaConversao = conversao.getConversion_rate();
                double valorConvertido = valor * taxaConversao; //vai vim de valorDIgitado da classe usuario

                DecimalFormat df = new DecimalFormat("#.00");
                System.out.println("\n>>>>>>>>>>>> O valor de " + df.format(valor) + " [" + codigoMoedaOrigem + "] equivale a " + df.format(valorConvertido) + " [" + codigoMoedaDestino + "]\n");
                Thread.sleep(5000);

        } else{
                System.out.println("Erro na Api: " + response.statusCode());
                System.out.println(response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
