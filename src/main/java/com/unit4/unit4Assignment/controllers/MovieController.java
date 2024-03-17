package com.unit4.unit4Assignment.controllers;

import com.unit4.unit4Assignment.dtos.MovieDto;
import com.unit4.unit4Assignment.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/all")
    public List<MovieDto> getAllMovies()
    {
        return movieService.getAllMovies();
    }

    @GetMapping("/get/{movieId}")
    public Optional<MovieDto> getMovieById(@PathVariable Long movieId)
    {
        return movieService.getMovieById(movieId);
    }

    @DeleteMapping("/delete/{movieId}")
    public void deleteMovieById(@PathVariable Long movieId)
    {
        movieService.deleteMovieById(movieId);
    }

    @PutMapping("/update")
    public void updateMovie(@RequestBody MovieDto movieDto)
    {
        movieService.updateMovie(movieDto);
    }

    @PostMapping("/add")
    public void addMovie(@RequestBody MovieDto movieDto)
    {
        movieService.addMovie(movieDto);
    }
}
