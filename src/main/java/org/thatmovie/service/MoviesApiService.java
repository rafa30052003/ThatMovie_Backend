package org.thatmovie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
