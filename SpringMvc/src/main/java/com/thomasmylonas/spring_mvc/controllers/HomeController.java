package com.thomasmylonas.spring_mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = {"/spring-mvc"})
public class HomeController {

    @RequestMapping(path = {"", "/", "/home"}, method = RequestMethod.GET)
    public String fetchHomePage() {
        return "home.html"; // "home"
    }

    @RequestMapping(path = {"/bootstrap-demo"}, method = RequestMethod.GET)
    public String fetchBootstrapDemo() {
        return "bootstrap_demo.html"; // "bootstrap_demo"
    }

    @RequestMapping(path = {"/jsp-form"}, method = RequestMethod.GET)
    public String fetchJspForm() {
        return "jsp_form.jsp";
    }

    @RequestMapping(path = {"/jsp-form-result"}, method = RequestMethod.POST)
    public String fetchJspFormResult() {
        return "jsp_form_result.jsp";
    }
}
