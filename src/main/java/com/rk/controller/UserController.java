package com.rk.controller;

import com.rk.model.User;
import com.rk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
//    @Autowired
//    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user){
        userRepository.save(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getById(@PathVariable long id){
        return userRepository.findById(id);
    }

//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void DeleteUser(@PathVariable int id){
//        userService.deleteById(id);
//    }

}
