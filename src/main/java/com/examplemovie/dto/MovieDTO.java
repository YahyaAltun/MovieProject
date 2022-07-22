package com.examplemovie.dto;

import com.examplemovie.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    @Id
    private Integer id;

    @NotNull(message="Movie Name can not be null")
    @NotBlank(message="Movie Name can not be white space")
    @Size(max=100, message="Movie Name '${validatedValue}' must be {max} chars long")
    private String name;

    private LocalDateTime updatedDate;

    @DecimalMax(value = "10",message = "Movie rate '${validatedValue}' must be less than {value}")
    @DecimalMin(value = "0",message = "Movie rate '${validatedValue}' must be more than {value}")
    private Double rate;

    public MovieDTO(Movie movie){
        this.id= movie.getId();
        this.name= movie.getName();
        this.rate= movie.getRate();
    }
}
