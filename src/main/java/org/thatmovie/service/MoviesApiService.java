package org.thatmovie.service;

import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thatmovie.repository.MoviesApiRepository;

import java.io.IOException;
@Service
public class MoviesApiService {

    private final MoviesApiRepository moviesApiRepository;

    @Autowired
    public MoviesApiService(MoviesApiRepository moviesApiRepository) {
        this.moviesApiRepository = moviesApiRepository;
    }

    public JsonArray getMovieListName(String movieName) throws IOException {
        return moviesApiRepository.getMoviesListName(movieName);
    }

    public JsonArray getMovieList(int page) throws IOException {
        return moviesApiRepository.getMoviesList(page);
    }
}
