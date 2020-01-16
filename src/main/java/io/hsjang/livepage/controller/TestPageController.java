package io.hsjang.livepage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestPageController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
    
    @GetMapping("/page/{page}")
    public String page() {
        return "page";
    }
}