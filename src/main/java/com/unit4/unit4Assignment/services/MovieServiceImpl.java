package com.unit4.unit4Assignment.services;

import com.unit4.unit4Assignment.dtos.MovieDto;
import com.unit4.unit4Assignment.entities.Movie;
import com.unit4.unit4Assignment.repositories.MovieRepository;
import com.unit4.unit4Assignment.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Override
    @Transactional
    public void addMovie(MovieDto movieDto)
    {
        Movie movie = new Movie(movieDto);
        movieRepository.saveAndFlush(movie);
    }

    @Override
    @Transactional
    public void updateMovie(MovieDto movieDto)
    {
        Optional<Movie> movieOptional = movieRepository.findById(movieDto.getId());
        movieOptional.ifPresent(movie -> {
            movie.setTitle(movieDto.getTitle());
            movieRepository.saveAndFlush(movie);
        });
    }

    @Override
    @Transactional
    public void deleteMovieById(Long movieId)
    {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        movieOptional.ifPresent(movie -> movieRepository.delete(movie));
    }

    @Override
    @Transactional
    public List<MovieDto> getAllMovies()
    {
        List<Movie> movieList = movieRepository.findAll();
        return movieList.stream().map(movie -> new MovieDto(movie)).collect(Collectors.toList());
    }

    @Override
    public Optional<MovieDto> getMovieById(Long movieId)
    {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (movieOptional.isPresent())
        {
            return Optional.of(new MovieDto(movieOptional.get()));
        }
        return Optional.empty();
    }
}
