package org.thatmovie.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thatmovie.model.DTO.MovieDTO;
import org.thatmovie.model.DTO.ResponseMovieDTO;
import org.thatmovie.repository.MoviesApiRepository;

import java.io.IOException;

@Service
public class MoviesApiService {

    private final MoviesApiRepository moviesApiRepository;





    /**
     *
     * @param moviesApiRepository
     */
    @Autowired
    public MoviesApiService(MoviesApiRepository moviesApiRepository) {
        this.moviesApiRepository = moviesApiRepository;
    }

    /**
     *
     * @param movieName
     * @return
     * @throws IOException
     */
    public ResponseMovieDTO getMovieListName(String movieName) throws IOException {
        return moviesApiRepository.getMoviesListName(movieName);
    }






    /**
     *
     * @param page
     * @return
     * @throws IOException
     */

    public ResponseMovieDTO getMovieList(int page) throws IOException {
        return moviesApiRepository.getMoviesList(page);
    }

    public ResponseMovieDTO getSimilarMovies(int id) throws IOException {
        return moviesApiRepository.getSimilarMovies(id);
    }







    
    /**
     * Obtiene la lista de películas populares desde el repositorio de la API de películas.
     *
     * @return la lista de películas populares
     */
    public ResponseMovieDTO getPopularList() throws IOException {
        return moviesApiRepository.getPopularMovie();
    }

    public ResponseMovieDTO getUpcomingMovies () throws IOException {
        return moviesApiRepository.getUpcomingMovies();
    }

    public ResponseMovieDTO getNowPlayingMovies () throws IOException {
        return moviesApiRepository.getNowPlayingMovies();
    }








    /**
     * Obtiene un MovieDTO por su ID.
     *
     * @param  id   el ID de la película a recuperar
     * @return      el MovieDTO con el ID especificado, o null si no se encuentra
     */
    public MovieDTO getMovieById(int id)  {
        try {
            return moviesApiRepository.getMovieById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
      return null;
    }






}
