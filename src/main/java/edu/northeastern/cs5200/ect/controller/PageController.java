package edu.northeastern.cs5200.ect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/DEQ_Buy")
    public String DEQ_Buy() {
        return "DEQ_Buy";
    }

    @GetMapping("/DEQ_Sell")
    public String DEQ_Sell() {
        return "DEQ_Sell";
    }

    @GetMapping("/IEEQ_Buy")
    public String IEEQ_Buy() {
        return "IEEQ_Buy";
    }

    @GetMapping("/IEEQ_Sell")
    public String IEEQ_Sell() {
        return "IEEQ_Sell";
    }

    @GetMapping("/IQ_Buy")
    public String IQ_Buy() {
        return "IQ_Buy";
    }

    @GetMapping("/IQ_Sell")
    public String IQ_Sell() {
        return "IQ_Sell";
    }
}
