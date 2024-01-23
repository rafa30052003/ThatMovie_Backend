package org.thatmovie.controller;


import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thatmovie.model.MovieDTO;
import org.thatmovie.model.ResponseMovieDTO;
import org.thatmovie.service.MoviesApiService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieApiController {
    private final MoviesApiService moviesApiService;
    @Autowired
    public MovieApiController(MoviesApiService moviesApiService) {
        this.moviesApiService = moviesApiService;
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseMovieDTO> getMovieList(@RequestParam("page") int page) throws IOException {
        ResponseMovieDTO result = moviesApiService.getMovieList(page);
        System.out.println(result);
        return new ResponseEntity<ResponseMovieDTO>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseMovieDTO getMovieListName(@RequestParam("movieName") String movieName) throws IOException {
        return moviesApiService.getMovieListName(movieName);
    }

}
