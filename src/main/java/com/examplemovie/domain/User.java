package com.examplemovie.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length=50,nullable=false)
    private String firstName;

    @Column(length=50,nullable=false)
    private String lastName;

    @Column(length=30,nullable=false,unique=true)
    private String email;

    @Column(length=120,nullable=false)
    private String password;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role userRole;

    @OneToMany(mappedBy = "createdForUser")
    private Set<Movie> movie=new HashSet<>();

    @OneToMany(mappedBy = "userComment")
    private Set<Comment> comment = new HashSet<>();

    @OneToOne
    @JoinColumn(name="user_id")
    private UserforRole userforRole;
}
