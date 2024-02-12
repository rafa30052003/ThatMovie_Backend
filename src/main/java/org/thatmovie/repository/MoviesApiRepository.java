package org.thatmovie.repository;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Repository;
import org.thatmovie.model.DTO.MovieDTO;
import org.thatmovie.model.DTO.ResponseMovieDTO;

import java.io.IOException;

@Repository
public class MoviesApiRepository {
    private static final OkHttpClient client = new OkHttpClient();
    private static final String apiKey = "b3edb8667c4b58c6c568c1046533ee7c";
    private static final String baseUrl = "https://api.themoviedb.org/3";

    private static String authToken;






    public static void setAuthToken(String token) {
        authToken = token;
    }









    /**
     * Obtiene una película por su ID.
     *
     * @param  id    el ID de la película a obtener
     * @return      el objeto DTO de película que representa la película obtenida
     */


    public static MovieDTO getMovieById(int id) throws IOException {

        String endpoint = baseUrl + "/movie/" + id;
        HttpUrl.Builder urlBuilder = HttpUrl.parse(endpoint).newBuilder();
        urlBuilder.addQueryParameter("api_key", apiKey);
        urlBuilder.addQueryParameter("append_to_response", "credits");

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .addHeader("Access-Control-Allow-Origin", "*")
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        MovieDTO result = new Gson().fromJson(responseBody, new TypeToken<MovieDTO>() {}.getType());

        return result;
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
        ResponseMovieDTO responseMovieDTO = new Gson().fromJson(responseBody, new TypeToken<ResponseMovieDTO>() {}.getType());
        for (MovieDTO movie : responseMovieDTO.getResults()) {
            MovieDTO detailedMovie = getMovieById(movie.getId());
            movie.setGenres(detailedMovie.getGenres());
        }

        // Devolver el ResponseMovieDTO actualizado con los detalles de género
        return responseMovieDTO;
    }







    /**
     * Obtiene la película popular desde la API.
     *
     * @return la respuesta de la película popular
     */
    public static ResponseMovieDTO getPopularMovie() throws IOException {

        String endpoint = baseUrl + "/movie/popular";

        HttpUrl.Builder urlBuilder = HttpUrl.parse(endpoint).newBuilder();
        urlBuilder.addQueryParameter("api_key", apiKey);

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

    /**
     * Obtiene la película popular desde la API.
     *
     * @return la respuesta de la película popular
     */
    public static ResponseMovieDTO getUpcomingMovies() throws IOException {

        String endpoint = baseUrl + "/movie/upcoming";

        HttpUrl.Builder urlBuilder = HttpUrl.parse(endpoint).newBuilder();
        urlBuilder.addQueryParameter("api_key", apiKey);

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

    public static ResponseMovieDTO getNowPlayingMovies() throws IOException {
        String endpoint = baseUrl + "/movie/now_playing";

        HttpUrl.Builder urlBuilder = HttpUrl.parse(endpoint).newBuilder();
        urlBuilder.addQueryParameter("api_key", apiKey);

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


    public static void main(String[] args) {

        try {
            ResponseMovieDTO moviesList = MoviesApiRepository.getMoviesList( 1);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(moviesList);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}





