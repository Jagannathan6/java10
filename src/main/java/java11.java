
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Slf4j
public class java11 {

    static String uri = "http://localhost:8080/hello";

    public static void main(String args[]) throws Exception {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        String name = "Jagan";

        HttpRequest request =
                HttpRequest.newBuilder().GET().uri(new URI(uri + "/" + name)).build();

        HttpResponse<String> response =
                 httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        log.info("Response Code is {} ", response.statusCode());
        log.info("Response Body is {}", response.body());

        HttpHeaders headers = response.headers();

        headers.map().forEach((k, v) -> log.info("Key is {} Value is {}", k, v));

    }
}
