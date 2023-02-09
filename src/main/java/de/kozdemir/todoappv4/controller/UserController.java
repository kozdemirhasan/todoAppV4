package de.kozdemir.todoappv4.controller;

import de.kozdemir.todoappv4.model.User;
import de.kozdemir.todoappv4.repository.UserRepository;
import de.kozdemir.todoappv4.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("sess")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginService loginService;

    @GetMapping("login")
    public String loginForm(Model model) {
        if (loginService.isLoggedIn())
            return "redirect:/todos";
        return "login-form";
    }

    @PostMapping("login")
    public String login(String email, String password, Model model) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            loginService.doLogIn(user);
            return "redirect:/todos";
        }
        model.addAttribute("error", true);
        return "login-form";
    }

    @GetMapping("logout")
    public String logout(Model model) {
        loginService.doLogOut();
        model.addAttribute("data", "Abgemeldet");
        return "redirect:/sess/login";
    }
}

