package com.kumaiscoding.notesappspring.controller;

import com.kumaiscoding.notesappspring.dao.UserRepository;
import com.kumaiscoding.notesappspring.entity.Note;
import com.kumaiscoding.notesappspring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/data")
    public List<String> getUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.toString());
        String username = authentication.getName();
        Optional<User> user = userRepository.findByUsername(username);


        if (user.isEmpty()) {
            throw new RuntimeException("User not found - " + username);
        }
        User u = user.get();

        return new ArrayList<>(Arrays.asList(u.getUsername(), u.getName()));
    }

    @PutMapping("/{username}")
    public User updateUser(@RequestBody User user, @PathVariable String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if(optionalUser.isEmpty()) {
            throw new RuntimeException();
        }

        User userToUpdate = optionalUser.get();
        userToUpdate.setName(user.getName());

        return userRepository.save(userToUpdate);
    }

    @DeleteMapping("/all/{username}")
    public String deleteUser(@PathVariable String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new RuntimeException("Employee id not found - " + username);
        }
        userRepository.delete(user.get());
        return "Deleted employee with id of " + username;
    }
}
