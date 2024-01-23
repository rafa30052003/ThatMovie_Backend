package org.thatmovie.repository;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.thatmovie.model.MovieDTO;
import org.thatmovie.model.ResponseMovieDTO;

import java.io.IOException;
import java.util.List;

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

    /**
     * Este método obtiene una lista de películas de la API basada en el número de página especificado.
     *
     * @param page el número de página para la lista de películas
     * @return objeto ResponseMovieDTO que contiene la lista de películas
     * @throws IOException si ocurre un error de E/S al realizar la llamada a la API
     */
    public static ResponseMovieDTO getMoviesList(int page) throws IOException {

        String endpoint = baseUrl + "/discover/movie";

        HttpUrl.Builder urlBuilder = HttpUrl.parse(endpoint).newBuilder();
        urlBuilder.addQueryParameter("api_key", apiKey);
        urlBuilder.addQueryParameter("page", String.valueOf(page));
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .addHeader("Access-Control-Allow-Origin", "*")
                .build();

        Response response = client.newCall(request).execute();

        String responseBody = response.body().string();
        ResponseMovieDTO result = new Gson().fromJson(responseBody, new TypeToken<ResponseMovieDTO>() {}.getType());

        return result;
    }


    /**
     * Este método obtiene una lista de películas basada en el nombre de película proporcionado.
     * Realiza una solicitud GET al punto final de búsqueda de películas con el nombre de película proporcionado como parámetro de consulta.
     * Luego analiza el cuerpo de la respuesta en un objeto ResponseMovieDTO utilizando la biblioteca Gson.
     *
     * @param movieName el nombre de la película a buscar
     * @return un objeto ResponseMovieDTO que contiene la lista de películas
     * @throws IOException si ocurre un error de E/S durante la solicitud HTTP
     */

    public static ResponseMovieDTO getMoviesListName(String movieName) throws IOException {
        String endpoint = baseUrl + "/search/movie";

        HttpUrl.Builder urlBuilder = HttpUrl.parse(endpoint).newBuilder();
        urlBuilder.addQueryParameter("api_key", apiKey);
        urlBuilder.addQueryParameter("query", movieName);

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .addHeader("Authorization", "Bearer " + authToken)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        ResponseMovieDTO result = new Gson().fromJson(responseBody, new TypeToken<ResponseMovieDTO>() {}.getType());


        return result;
    }





}





