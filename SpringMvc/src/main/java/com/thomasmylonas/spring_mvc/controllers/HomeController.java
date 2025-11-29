package com.thomasmylonas.spring_mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = {"/spring-mvc"})
public class HomeController {

    @RequestMapping(path = {"", "/", "/home"}, method = RequestMethod.GET)
    public String fetchHomePage() {
        return "home";
    }
}
