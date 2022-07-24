package com.examplemovie.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name="tbl_userRole")
@Entity
public class UserforRole {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(length=50,nullable=false)
    private String firstName;

    @Column(length=50,nullable=false)
    private String lastName;

    @Column(length=20,nullable = false,unique = true)
    private String userName;


    @Column(length=255,nullable = false)
    private String password;


    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="tbl_userforrole_role",joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns =@JoinColumn(name="role_id"))
    private Set<Role> roles=new HashSet<>();


    @JsonIgnore
    @OneToOne(mappedBy = "userforRole")
    private User user;
}
