package org.thatmovie.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thatmovie.model.DTO.ResponseMovieDTO;
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

}
