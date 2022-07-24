package com.examplemovie.service;

import com.examplemovie.domain.User;
import com.examplemovie.dto.UserDTO;
import com.examplemovie.exception.ConflictException;
import com.examplemovie.exception.ResourceNotFoundException;
import com.examplemovie.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{

    private IUserRepository iUserRepository;


    public UserServiceImpl(IUserRepository iUserRepository){
        this.iUserRepository=iUserRepository;
    }

    @Override
    public void createUser(User user) {
        if (iUserRepository.existsByEmail(user.getEmail())){
            throw new ConflictException("Email already exist!");
        }
        iUserRepository.save(user);
    }

    @Override
    public void updateUser(User user,Long id) {
        User foundUser = iUserRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("User not found id"+id));

        boolean existEmail=iUserRepository.existsByEmail(user.getEmail());

        if (existEmail){
            throw new ResourceNotFoundException("Email already exist!");
        }
        if (!foundUser.getPassword().equals(user.getPassword())){
            user.setId(foundUser.getId());
            foundUser.setFirstName(user.getFirstName());
            foundUser.setLastName(user.getLastName());
            foundUser.setEmail(user.getEmail());
            foundUser.setPassword(user.getPassword());
            foundUser.setUserRole(user.getUserRole());
            foundUser.setMovie(user.getMovie());
            foundUser.setComment(user.getComment());
            iUserRepository.save(foundUser);
        }
        if (!foundUser.getEmail().equals(user.getEmail())){
            user.setId(foundUser.getId());
            foundUser.setFirstName(user.getFirstName());
            foundUser.setLastName(user.getLastName());
            foundUser.setEmail(user.getEmail());
            foundUser.setPassword(user.getPassword());
            foundUser.setUserRole(user.getUserRole());
            foundUser.setMovie(user.getMovie());
            foundUser.setComment(user.getComment());
            iUserRepository.save(foundUser);

        }
    }

    @Override
    public void deleteUser(Long id) {
        User user = iUserRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("User not found id"+id));
        iUserRepository.delete(user);
    }

    @Override
    public UserDTO findUserDTOById(Long id) {
        return iUserRepository.findUserDTOById(id).orElseThrow(()->
                new ResourceNotFoundException("User not found id"+id));
    }

    @Override
    public List<User> findAll() {
        return iUserRepository.findAll();
    }
}
