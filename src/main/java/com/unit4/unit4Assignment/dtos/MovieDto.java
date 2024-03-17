package com.unit4.unit4Assignment.dtos;

import com.unit4.unit4Assignment.entities.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto implements Serializable {
    private Long id;
    private String title;

    public MovieDto(Movie movie)
    {
        if (movie.getId() != null)
        {
            this.id = movie.getId();
        }
        if (movie.getTitle() != null)
        {
            this.title = movie.getTitle();
        }
    }
}
