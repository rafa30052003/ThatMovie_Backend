package org.thatmovie.repository;

import com.google.gson.*;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MoviesApiRepository {
    private static final OkHttpClient client = new OkHttpClient();
    private static final String apiKey = "b3edb8667c4b58c6c568c1046533ee7c";
    private static final String baseUrl = "https://api.themoviedb.org/3";
    private static String authToken;

    public static void setAuthToken(String token) {
        authToken = token;
    }

    public static JsonArray getMoviesList(int page) throws IOException {
        String endpoint = baseUrl + "/discover/movie";

        HttpUrl.Builder urlBuilder = HttpUrl.parse(endpoint).newBuilder();
        urlBuilder.addQueryParameter("api_key", apiKey);
        urlBuilder.addQueryParameter("page", String.valueOf(page));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .addHeader("Authorization", "Bearer " + authToken)
                .build();

        Response response = client.newCall(request).execute();

        // Convertir el flujo de caracteres a cadena y analizarla como JSON
        String responseBody = response.body().string();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonResponse = jsonParser.parse(responseBody).getAsJsonObject();

        // Obtener y devolver el arreglo de resultados del objeto JSON
        return jsonResponse.getAsJsonArray("results");
    }

    public static void main(String[] args) {
        try {
            JsonArray moviesList = MoviesApiRepository.getMoviesList(1);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(moviesList);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
