package com.example.hac.example.api.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController {

    @GetMapping("/")
    public ModelAndView test() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("fragments/index/content");
        return mv;
    }
}
