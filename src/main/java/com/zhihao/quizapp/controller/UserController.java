package com.zhihao.quizapp.controller;

import com.zhihao.quizapp.model.User;
import com.zhihao.quizapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-01-31 7:50 PM
 */


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") User user, Model model){
        boolean success = userService.registerNewUser(user);
        if(!success){
            model.addAttribute("error","Register not success!");
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping({"/","/login"})
    public String showLoginPage(Model model, HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user != null){
            if(user.getIsAdmin() == 1){
                return("redirect:/admin/home");
            }else{
                return("redirect:/home");
            }
        }
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("user") User user, Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser != null) {
            if (sessionUser.getIsAdmin() == 1) {
                return "redirect:/admin/home";
            } else {
                return "redirect:/home";
            }
        }

        User userByEmail = userService.findUserByEmail(user.getEmail());
        if (userByEmail == null) {
            model.addAttribute("error", "User does not exist");
            return "login";
        }

        User dbUser = userService.login(user.getEmail(), user.getPassword());
        if (dbUser == null) {
            model.addAttribute("error", "Password does not match");
            return "login";
        } else if (dbUser.getIsActive() != 1) {
            model.addAttribute("error", "User has been suspended");
            return "login";
        } else {
            session.setAttribute("user", dbUser);
            if (dbUser.getIsAdmin() == 1) {
                return "redirect:/admin/home";
            } else {
                return "redirect:/home";
            }
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
