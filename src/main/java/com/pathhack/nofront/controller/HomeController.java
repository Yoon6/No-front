package com.pathhack.nofront.controller;

import com.pathhack.nofront.domain.Image;
import com.pathhack.nofront.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public String getMainPage() {
        return "index";
    }

    @GetMapping("/object")
    public String getObjectPage(@RequestParam("nickname") String nickname, Model model) {

        model.addAttribute("nickname", nickname);

        return "object";
    }

    @GetMapping("/success")
    public String getSuccessPage() {
        return "success";
    }

    @GetMapping("/fail")
    public String getFailPage() {
        return "fail";
    }


    @GetMapping("/game")
    public String getGamePage(@RequestParam("nickname") String nickname, Model model) {
        model.addAttribute("nickname", nickname);

        return "game";
    }

    @PostMapping("/join")
    public String postNickname(@RequestParam("nickname") String nickname) {

        try {
//            homeService.makeSocketConnection();
            homeService.saveUser(nickname);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/object";
    }

}
