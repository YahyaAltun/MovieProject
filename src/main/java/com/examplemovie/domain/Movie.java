package com.examplemovie.domain;

import com.examplemovie.domain.enums.MovieCategories;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="tbl_movie")
public class Movie {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(length=30,nullable=false,unique=true)
    private String name;

    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="MM/dd/yyyy HH:mm:ss",timezone ="Turkey")
    private LocalDateTime updatedDate=LocalDateTime.now();

    @Column(length = 2)
    private Double rate;


    @Enumerated(EnumType.STRING)
    @Column(length=20)
    private MovieCategories movieCategories;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User createdForUser;

    @OneToMany(mappedBy = "movieComment")
    private Set<Comment> comment=new HashSet<>();
}
