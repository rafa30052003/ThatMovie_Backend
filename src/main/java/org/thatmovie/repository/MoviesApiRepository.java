package org.thatmovie.repository;

import com.google.gson.*;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
@Repository
public class MoviesApiRepository {
    private static final OkHttpClient client = new OkHttpClient();
    private static final String apiKey = "b3edb8667c4b58c6c568c1046533ee7c";
    private static final String baseUrl = "https://api.themoviedb.org/3";
    //private static OkHttpClient client;
   // private static String apiKey;
   //private static String baseUrl;
    private static String authToken;
/*
    @Value("${api.client}")
    public void setClient(String client) {
        MoviesApiRepository.client = new OkHttpClient.Builder().build();
    }

    @Value("${api.key}")
    public void setApiKey(String key) {
        MoviesApiRepository.apiKey = key;
    }

    @Value("${api.url}")
    public void setBaseUrl(String url) {
        MoviesApiRepository.baseUrl = url;
    }

 */

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


        String responseBody = response.body().string();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonResponse = jsonParser.parse(responseBody).getAsJsonObject();


        return jsonResponse.getAsJsonArray("results");
    }

    public static JsonArray getMoviesListName(String movieName) throws IOException {
        String endpoint = baseUrl + "/search/movie";

        HttpUrl.Builder urlBuilder = HttpUrl.parse(endpoint).newBuilder();
        urlBuilder.addQueryParameter("api_key", apiKey);
        urlBuilder.addQueryParameter("query", movieName);

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .addHeader("Authorization", "Bearer " + authToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonResponse = jsonParser.parse(responseBody).getAsJsonObject();


            return jsonResponse.getAsJsonArray("results");
        }
    }



    public static void main(String[] args) {
        try {
            JsonArray moviesList = MoviesApiRepository.getMoviesListName( "Goodfellas");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(moviesList);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
