package com.examplemovie.repository;

import com.examplemovie.domain.User;
import com.examplemovie.dto.UserDTO;
import com.examplemovie.exception.ConflictException;
import com.examplemovie.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email) throws ConflictException;

    @Query("SELECT new com.examplemovie.dto.UserDTO(u) FROM User u WHERE u.id=:id")
    Optional<UserDTO> findUserDTOById(@Param("id") Long id);



}
