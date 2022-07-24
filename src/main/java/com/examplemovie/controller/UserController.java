package com.examplemovie.controller;

import com.examplemovie.domain.User;
import com.examplemovie.dto.UserDTO;
import com.examplemovie.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl userService;


    @PostMapping
    public ResponseEntity<Map<String,String>> createUser(@Valid @RequestBody User user){
        userService.createUser(user);
        Map<String,String> map=new HashMap<>();
        map.put("message", "User created successfuly");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    //http://localhost:8081/user/update/6
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole ('ROLE_FREE') or hasRole('ROLE_ONEMONTH') or hasRole('ROLE_THREEMONTH') or hasRole('ROLE_SİXMONTH') or hasRole('ROLE_ONEYEAR')")
    public ResponseEntity<Map<String,String>> updateUser(@PathVariable Long id, @Valid @RequestBody User user){
        userService.updateUser(user,id);
        Map<String,String> map=new HashMap<>();
        map.put("message", "User updated successfuly");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    //http://localhost:8081/user/delete/2
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole ('ROLE_FREE') or hasRole('ROLE_ONEMONTH') or hasRole('ROLE_THREEMONTH') or hasRole('ROLE_SİXMONTH') or hasRole('ROLE_ONEYEAR')")
    public ResponseEntity<Map<String,String>> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        Map<String,String> map=new HashMap<>();
        map.put("message", "User deleted successfuly");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    //http://localhost:8081/user/get/3 TODO
    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole ('ROLE_FREE') or hasRole('ROLE_ONEMONTH') or hasRole('ROLE_THREEMONTH') or hasRole('ROLE_SİXMONTH') or hasRole('ROLE_ONEYEAR')")
    public ResponseEntity<UserDTO> getUserDTOById(@PathVariable("id") Long id){
        UserDTO userDTO = userService.findUserDTOById(id);
        return  ResponseEntity.ok(userDTO);
    }

    //http://localhost:8081/user/get
    @GetMapping("/get")
    @PreAuthorize("hasRole ('ROLE_FREE') or hasRole('ROLE_ONEMONTH') or hasRole('ROLE_THREEMONTH') or hasRole('ROLE_SİXMONTH') or hasRole('ROLE_ONEYEAR')")
    public ResponseEntity<List<User>> getAll(){
        List<User> user = userService.findAll();
        return  ResponseEntity.ok(user);
    }
}
