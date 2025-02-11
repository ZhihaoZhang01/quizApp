package com.zhihao.quizapp.controller;

import com.zhihao.quizapp.model.User;
import com.zhihao.quizapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User addUser(@RequestBody User user) {
        User newUser = new User();

        boolean success = userService.registerNewUser(user);
        if (!success) {
            throw new RuntimeException("User already exists");
        }
        return userService.findUserByEmail(user.getEmail());
    }

    @DeleteMapping
    public String deleteUser(@RequestParam("userId") int userId) {
        userService.deleteUser(userId);
        return "User deleted successfully";
    }


    @PatchMapping("/{userId}/status")
    public String updateUserStatus(@PathVariable("userId") int userId,
                                   @RequestParam("activate") int activate) {
        int newStatus = activate == 1 ? 1 : 0;
        userService.updateUserStatus(userId, newStatus);
        return "User status updated successfully";
    }

    @GetMapping
    public Object getUser(@RequestParam(value = "userId", required = false) Integer userId) {
        if (userId != null) {
            return userService.findUserById(userId);
        } else {
            return userService.getAllUsers();
        }
    }
}
