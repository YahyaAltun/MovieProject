package com.examplemovie.repository;

import com.examplemovie.domain.UserforRole;
import com.examplemovie.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserforRoleRepository extends JpaRepository {
    Optional<UserforRole> findByUserName(String userName) throws ResourceNotFoundException;
}
