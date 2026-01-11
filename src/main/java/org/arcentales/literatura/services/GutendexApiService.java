package org.arcentales.literatura.services;
import org.arcentales.literatura.models.BookData;
import org.arcentales.literatura.models.GutendexResponse;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class GutendexApiService {

    private static final String BASE_URL = "https://gutendex.com/books/";

    private final HttpClient client = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    private final ObjectMapper mapper = new ObjectMapper();

    public List<BookData> getBooks() {
        return get(BASE_URL).results();
    }

    public List<BookData> search(String query) {
        return get(BASE_URL + "?search=" + query).results();
    }

    private GutendexResponse get(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 300) {
                throw new RuntimeException("HTTP error " + response.statusCode());
            }

            return mapper.readValue(response.body(), GutendexResponse.class);

        } catch (Exception e) {
            throw new RuntimeException("Gutendex API error", e);
        }
    }
}