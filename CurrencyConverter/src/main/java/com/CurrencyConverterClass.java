import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.io.IOException;
import java.util.Scanner;

public class CurrencyConverterClass {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escribe la moneda desde la cual convertir");
        String convertirDe = scanner.nextLine().toUpperCase();
        System.out.println("Escribe la moneda a la que convertir");
        String convertirA = scanner.nextLine().toUpperCase();
        System.out.println("Escribe la cantidad a convertir");
        BigDecimal cantidad = scanner.nextBigDecimal();

        String urlString = "https://v6.exchangerate-api.com/v6/eb0327d3e0af75fcb8974120/latest/" + convertirDe;

        OkHttpClient cliente = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlString)
                .get()
                .build();
        Response response = cliente.newCall(request).execute();
        String stringResponse = response.body().string();
        System.out.println("Respuesta del servidor:");
        System.out.println(stringResponse);


        JSONObject jsonObject = new JSONObject(stringResponse);
        JSONObject ratesObject = jsonObject.getJSONObject("conversion_rates");
        BigDecimal rate = ratesObject.getBigDecimal(convertirA);

        BigDecimal result = rate.multiply(cantidad);
        System.out.println("Resultado de la conversión:");
        System.out.println(result);
    }
}
