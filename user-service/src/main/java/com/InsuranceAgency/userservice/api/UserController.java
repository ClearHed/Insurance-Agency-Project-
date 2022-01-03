package com.InsuranceAgency.userservice.api;


import com.InsuranceAgency.userservice.Service.UserService;
import com.InsuranceAgency.userservice.api.dto.UserDto;
import com.InsuranceAgency.userservice.repo.model.User;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable int id) {
        try {
            User user = userService.getUserByID(id);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<JSONObject> createUser(@RequestBody UserDto user) {
        try {
            String username = user.getUsername();
            String password = user.getPassword();
            String email = user.getEmail();

            int id = userService.createUser(username, password, email);

            return ResponseEntity.created(URI.create("/users/" + id)).build();
        } catch (IllegalArgumentException e) {
            JSONObject response = new JSONObject();
            response.put("error", "incorrect data");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }

}

