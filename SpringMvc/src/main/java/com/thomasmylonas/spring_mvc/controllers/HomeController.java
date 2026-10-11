package com.thomasmylonas.spring_mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = {"/spring-mvc"})
public class HomeController {

    /**
     * Endpoint:
     * - GET, "http://localhost:8080/spring-mvc/home"
     *
     * @return The "home" page
     */
    @RequestMapping(path = {"", "/", "/home"}, method = RequestMethod.GET)
    public String homePage() {
        return "html_views/home.html";
    }

    /**
     * Endpoint:
     * - GET, "http://localhost:8080/spring-mvc/bootstrap-demo"
     *
     * @return The "bootstrap_demo" page
     */
    @RequestMapping(path = {"/bootstrap-demo"}, method = RequestMethod.GET)
    public String bootstrapDemo() {
        return "html_views/bootstrap_demo.html";
    }

    /**
     * Endpoint:
     * - GET, "http://localhost:8080/spring-mvc/jsp-form"
     *
     * @return The "jsp_form" page
     */
    @RequestMapping(path = {"/jsp-form"}, method = RequestMethod.GET)
    public String jspForm() {
        return "jsp_views/jsp_form.jsp";
    }

    /**
     * Endpoint:
     * - POST, "http://localhost:8080/spring-mvc/jsp-form-result"
     *
     * @return The "jsp_form_result" page
     */
    @RequestMapping(path = {"/jsp-form-result"}, method = RequestMethod.POST)
    public String jspFormResult() {
        return "jsp_views/jsp_form_result.jsp";
    }
}
