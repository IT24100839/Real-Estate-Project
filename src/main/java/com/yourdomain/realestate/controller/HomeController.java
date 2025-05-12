package com.yourdomain.realestate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Simple controller for the home page.
 */
@Controller
public class HomeController {

    /**
     * Handles requests for the root URL ("/").
     * @return The name of the home page template.
     */
    @GetMapping("/")
    public String home() {
        return "home"; // Return the name of the Thymeleaf template (home.html)
    }
}
