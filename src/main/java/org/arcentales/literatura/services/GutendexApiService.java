package org.arcentales.literatura.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GutendexApiService {

    private static final String GUTENDEX_BASE_URL = "https://gutendex.com/books";
    private final HttpClient client;

    public GutendexApiService() {
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    public String makeRequest(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .header("Accept", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                return response.body();
            } else {
                throw new RuntimeException("Error en la respuesta de la API: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error al realizar la petición: " + e.getMessage(), e);
        }
    }

    public String getBooks() {
        return makeRequest(GUTENDEX_BASE_URL);
    }

    public String getBooksBySearch(String query) {
        String url = GUTENDEX_BASE_URL + "?search=" + query;
        return makeRequest(url);
    }

    public String getBookById(Long id) {
        String url = GUTENDEX_BASE_URL + "/" + id;
        return makeRequest(url);
    }
}