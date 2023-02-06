package de.kozdemir.todoappv4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/")
    public String index(Model model) {
        // model.addAttribute = Weitergabe von Daten an die View
        model.addAttribute("headline", "Herzlich Willkommen bei Spring");
        model.addAttribute("ac", "home");
        model.addAttribute("content", "Das ist das Haus von Nikigraus...");
        return "standard"; // Verweist auf /WEB-INF/standard.jsp (siehe __application.yaml
        // )
    }

    @GetMapping("/team")
    public String team(Model model) {
        model.addAttribute("headline", "Unser Team");
        model.addAttribute("ac", "team");
        model.addAttribute("content", "Das sind alle unsere Team-Mitglieder...");
        return "standard";
    }


}
