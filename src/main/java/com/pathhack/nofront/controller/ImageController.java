package com.pathhack.nofront.controller;

import com.pathhack.nofront.domain.Image;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {
    @RequestMapping(value = "/updateSomeData", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void uploadImage(Image image) {

    }
}
