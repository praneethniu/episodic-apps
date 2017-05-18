package com.example.users;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsersController {

    private UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users = new ArrayList();
        userRepository.findAll()
                .forEach(users::add);

        return users;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        userRepository.save(user);

        return user;
    }
}
