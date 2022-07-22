package com.examplemovie.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Comment {

    @Id
    private Long id;

    @Column(length = 150)
    private String comment;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM/dd/yyyy HH:mm:ss",timezone ="Turkey")
    private LocalDateTime createdCommentDate=LocalDateTime.now();

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    private Movie movieComment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userComment;
}
