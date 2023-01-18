package com.ista.sample.service;

import com.ista.sample.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Iterable<User> findAll();
    Page<User> finsAll(Pageable pageable);
    Optional<User> findById(Integer id);
    User save(User user);
    void deleteById(Integer id);

}
