package br.com.alura.conversordemoedas;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.Properties;

public class ApiConexao {
    private static String API_KEY;

    static {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
            API_KEY = properties.getProperty("API_KEY");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

            URI endereco = URI.create("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + codigoMoedaOrigem + "/" + codigoMoedaDestino);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(endereco).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() ==200) {
                String json = response.body();
                System.out.println(json);

                Gson gson = new Gson();
                Conversao conversao = gson.fromJson(json, Conversao.class);

                double taxaConversao = conversao.getConversion_rate();
                double valorConvertido = valor * taxaConversao;

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
