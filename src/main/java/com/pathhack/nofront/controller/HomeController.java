package com.pathhack.nofront.controller;

import com.pathhack.nofront.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public String getMainPage() {
        return "index";
    }

    @GetMapping("/object")
    public String getObjectPage() {
        return "object";
    }

    @GetMapping("/game")
    public String getGamePage() {
        return "game";
    }


    @PostMapping("/join")
    public String postNickname(@RequestParam("nickname") String nickname) {

        try {
            homeService.makeSocketConnection();
            homeService.saveUser(nickname);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/object";
    }
}
