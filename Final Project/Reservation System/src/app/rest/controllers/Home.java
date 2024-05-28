package app.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {

    @GetMapping("/")
    public String home() {
        return "index"; // Assumes your main HTML file is named "index.html"
    }
}