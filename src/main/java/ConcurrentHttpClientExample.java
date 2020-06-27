import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
public class ConcurrentHttpClientExample {

    static String uri = "http://localhost:8080/hello/";

    public static void main(String args[]) throws Exception {
        List<URI> names = new ArrayList<>();
        names.add(new URI(uri +"Jagan"));
        names.add(new URI(uri + "Rahul"));
        names.add(new URI(uri + "Test"));

        HttpClient httpClient = HttpClient.newHttpClient();

        List<CompletableFuture<String>> responseList = names.stream().map(name -> httpClient.sendAsync(
                HttpRequest.newBuilder().GET().uri(name).build(),
                HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body)).collect(Collectors.toList());

        for (CompletableFuture<String> response : responseList) {
            log.info(response.get());
        }

    }
}
