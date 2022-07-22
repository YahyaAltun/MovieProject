package com.examplemovie.service;

import com.examplemovie.domain.User;
import com.examplemovie.dto.UserDTO;

import java.util.List;

public interface IUserService {
    void createUser(User user);
    void updateUser(User user,Long id);
    void deleteUser(Long id);
    UserDTO findUserDTOById(Long id);

    List<User> findAll();
}
