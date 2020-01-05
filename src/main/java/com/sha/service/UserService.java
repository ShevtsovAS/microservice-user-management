package com.sha.service;

import com.sha.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveUser(User user);

    Optional<User> findByUsername(String username);

    List<String> findUsers(List<Long> ids);

}
