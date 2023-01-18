package com.ista.sample.controller;

import com.ista.sample.model.User;
import com.ista.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
public class UserCtrl {

    @Autowired
    private UserService userService;

    @GetMapping("/user/getAll")
    public List<User> getAll() {
        List<User> users = StreamSupport
                .stream(userService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return users;
    }

    @PostMapping("/user/save")
    public ResponseEntity<?> create(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("id") Integer id) {
        Optional<User> current = userService.findById(id);
        if (!current.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        current.get().setNombre(user.getNombre());
        current.get().setClave(user.getClave());
        current.get().setEmail(user.getEmail());
        current.get().setEstado(user.getEstado());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(current.get()));
    }

}

