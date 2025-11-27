package com.thomasmylonas.spring_mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping(path = {"/"})
public class PagesController {

    @RequestMapping(path = {"", "home"}, method = RequestMethod.GET)
    public String getHomePage() {
        return "home";
    }
}
