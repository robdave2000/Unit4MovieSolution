package com.unit4.unit4Assignment.entities;

import com.unit4.unit4Assignment.dtos.MovieDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    public Movie(MovieDto movieDto)
    {
        if (movieDto.getTitle() != null)
        {
            this.title = movieDto.getTitle();
        }
    }
}
