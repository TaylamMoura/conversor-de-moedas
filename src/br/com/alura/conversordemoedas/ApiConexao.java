package br.com.alura.conversordemoedas;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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

    public static double obterTaxaConversao(String codigoMoedaOrigem, String codigoMoedaDestino) throws Exception{
        URI endereco = URI.create("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + codigoMoedaOrigem + "/" + codigoMoedaDestino);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() ==200) {
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
             return jsonObject.get("conversion_rate").getAsDouble();
        } else {
            throw new IOException("Erro na API: " + response.statusCode());
        }
    }
}
