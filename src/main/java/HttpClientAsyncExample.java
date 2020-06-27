import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class HttpClientAsyncExample {

    static String uri = "http://localhost:8080/hello";

    public static void main(String args[]) throws URISyntaxException {

        HttpClient httpClient = HttpClient.newHttpClient();

        String name = "Jagan";

        HttpRequest request =
                HttpRequest.newBuilder().GET().uri(new URI(uri + "/" + name)).build();

        CompletableFuture<HttpResponse<String>> response =
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        HttpResponse<String> responseString = response.join();

        log.info("Response Code is {} ", responseString.statusCode());
        log.info("Response Body is {}", responseString.body());

    }
}
