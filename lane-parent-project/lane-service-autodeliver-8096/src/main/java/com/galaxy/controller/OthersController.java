package com.galaxy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/others")
public class OthersController {



    @GetMapping("/test")
    public String findResumeOpenState() {
        return "others/test!";
    }
}
