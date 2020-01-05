package com.sha.controller;

import com.sha.model.Role;
import com.sha.model.User;
import com.sha.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        userService.saveUser(user);
    }

    @GetMapping
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        return ResponseEntity.ok(userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED)));
    }

    @PostMapping("/names")
    public ResponseEntity<List<String>> getUsers(@RequestBody List<Long> userIds) {
        return ResponseEntity.ok(userService.findUsers(userIds));
    }
}
