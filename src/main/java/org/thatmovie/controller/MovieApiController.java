package org.thatmovie.controller;


import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thatmovie.service.MoviesApiService;

import java.io.IOException;

@RestController
@RequestMapping("/api/movies")
public class MovieApiController {
    private final MoviesApiService moviesApiService;
    @Autowired
    public MovieApiController(MoviesApiService moviesApiService) {
        this.moviesApiService = moviesApiService;
    }

    @GetMapping("/list")
    public JsonArray getMovieList(@RequestParam("page") int page) throws IOException {
        return moviesApiService.getMovieList(page);

    }

}
