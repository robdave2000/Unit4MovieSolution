package com.unit4.unit4Assignment.services;

import com.unit4.unit4Assignment.dtos.MovieDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    @Transactional
    void addMovie(MovieDto movieDto);

    @Transactional
    void updateMovie(MovieDto movieDto);

    @Transactional
    void deleteMovieById(Long movieId);

    @Transactional
    List<MovieDto> getAllMovies();

    Optional<MovieDto> getMovieById(Long movieId);
}
