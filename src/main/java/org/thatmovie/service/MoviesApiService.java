package org.thatmovie.service;

import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thatmovie.model.MovieDTO;
import org.thatmovie.model.ResponseMovieDTO;
import org.thatmovie.repository.MoviesApiRepository;

import java.io.IOException;
import java.util.List;

@Service
public class MoviesApiService {

    private final MoviesApiRepository moviesApiRepository;

    @Autowired
    public MoviesApiService(MoviesApiRepository moviesApiRepository) {
        this.moviesApiRepository = moviesApiRepository;
    }

    public ResponseMovieDTO getMovieListName(String movieName) throws IOException {
        return moviesApiRepository.getMoviesListName(movieName);
    }

    public ResponseMovieDTO getMovieList(int page) throws IOException {
        return moviesApiRepository.getMoviesList(page);
    }
}
