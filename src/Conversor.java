import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Set;


public class Conversor {

    private static String apiKey = "dec2c2850ca4e187f1c5a115";

    public static double getData(String baseCurrency, String targetCurrency, double amount)
            throws IOException, InterruptedException {
        String apiUrl = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" +
                baseCurrency + "/" + targetCurrency + "/" + amount;

        // Realizar la solicitud HTTP
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();


        try {
            // Realizar la solicitud HTTP para obtener la tasa de conversión de USD a EUR

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar si la solicitud fue exitosa
            if (response.statusCode() == 200) {
                // Convertir la respuesta JSON a un objeto JsonObject
                JsonObject jsonResponse = new Gson().fromJson(response.body(), JsonObject.class);

                // Obtener la tasa de conversión y el resultado
                double conversionRate = jsonResponse.get("conversion_rate").getAsDouble();
                double conversionResult = jsonResponse.get("conversion_result").getAsDouble();


                // Imprimir el resultado de la conversión
                System.out.println(amount + baseCurrency + "  son equivalentes a " + conversionResult + targetCurrency);

                return conversionResult;
            } else {
                // Si la solicitud no fue exitosa, imprimir un mensaje de error
                System.out.println("Error al obtener la tasa de conversión: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            // Manejar las excepciones y imprimir un mensaje de error
            System.out.println("Error al realizar la solicitud: " + e.getMessage());
        }
        return -1; // Valor de retorno por defecto en caso de error
    }


//


}
