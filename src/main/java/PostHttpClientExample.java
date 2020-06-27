import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class PostHttpClientExample {

    static String uri = "http://localhost:8080/hello";

    public static void main(String args[]) throws URISyntaxException {
        Gson gson = new Gson();

        Car car = Car.builder().name("Maruti").color("BLUE").build();
        HttpClient httpClient = HttpClient.newHttpClient();

        log.info(gson.toJson(car));

        CompletableFuture<HttpResponse<String>> clientResponse = httpClient.sendAsync(
                HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(gson.toJson(car)))
                                    .uri(new URI("http://localhost:8081/v2/car"))
                                    .header("Content-Type","application/json").build(),
                HttpResponse.BodyHandlers.ofString()
        );

        HttpResponse<String> response = clientResponse.join();

        log.info("Response String {} Response Code {}", response.body(), response.statusCode());


    }
}
