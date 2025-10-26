package com.smartspend.controller.modelcontroller;


import com.smartspend.model.User;
import com.smartspend.service.modelservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if(userService.getUserByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }

      User user1 = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1.toString());
    }

    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public User getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {

        if(userService.getUserById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with id " + id + " does not exist");


        }
        userService.deleteUserById(id);
        return ResponseEntity.ok().body("User deleted successfully");
}

}
