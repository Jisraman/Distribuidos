import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.time.Duration;
public class HttpClientSynchronous {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {

        String requestBody = "17576000,IPN";

        long startTime = System.currentTimeMillis();

        HttpRequest request = HttpRequest.newBuilder()
                
                .uri(URI.create("http://localhost:8080/searchtoken")) // Cambia la URL al servidor adecuado
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .header("Content-Type", "text/plain; charset=UTF-8")
                .POST(BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        long endTime = System.currentTimeMillis();

        System.out.println("Tiempo de procesamiento en el servidor: " + (endTime - startTime) + " ms");

        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ": " + v));

        System.out.println("Código de estado: " + response.statusCode());

        System.out.println("Respuesta del servidor: " + response.body());
    }
}
