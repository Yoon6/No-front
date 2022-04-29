package com.pathhack.nofront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getMainPage() {
        return "index";
    }

    @GetMapping("/object")
    public String getObjectPage() {
        return "object";
    }
}
