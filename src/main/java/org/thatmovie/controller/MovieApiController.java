package org.thatmovie.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thatmovie.model.DTO.MovieDTO;
import org.thatmovie.model.DTO.ResponseMovieDTO;
import org.thatmovie.model.PlayList;
import org.thatmovie.service.MoviesApiService;

import java.io.IOException;

@RestController
@RequestMapping("/api/movies")
public class MovieApiController {
    private final MoviesApiService moviesApiService;

    /**
     *
     * @param moviesApiService
     */
    @Autowired
    public MovieApiController(MoviesApiService moviesApiService) {
        this.moviesApiService = moviesApiService;
    }

    /**
     *
     * @param page
     * @return
     * @throws IOException
     */
    @GetMapping("/list")
    public ResponseEntity<ResponseMovieDTO> getMovieList(@RequestParam("page") int page) throws IOException {
        ResponseMovieDTO result = moviesApiService.getMovieList(page);
        System.out.println(result);
        return new ResponseEntity<ResponseMovieDTO>(result, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     *
     * @param movieName
     * @return
     * @throws IOException
     */

    @GetMapping("/search")
    public ResponseMovieDTO getMovieListName(@RequestParam("movieName") String movieName) throws IOException {
        return moviesApiService.getMovieListName(movieName);
    }
    /**
     +     * Obtiene la lista de películas populares desde la API.
     +     *
     +     * @return         	la lista de películas populares
     +     */
    @GetMapping("/popular")
    public ResponseMovieDTO getPopularList() throws IOException {
        return moviesApiService.getPopularList();
    }
    /**
     * Obtiene una película por su ID.
     *
     * @param  id   el ID de la película a obtener
     * @return      el ResponseEntity que contiene el MovieDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable("id") int id){
        MovieDTO movie = moviesApiService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/upcoming")
    public ResponseMovieDTO getUpcomingMovies() throws IOException {
        return moviesApiService.getUpcomingMovies();
    }

    @GetMapping("/now_playing")
    public ResponseMovieDTO getNowPlayingMovies() throws IOException {
        return moviesApiService.getNowPlayingMovies();
    }

    @GetMapping("/similar/{id}")
    public ResponseMovieDTO getSimilarMovies(@PathVariable("id") int id) throws IOException {
        return moviesApiService.getSimilarMovies(id);
    }

}
