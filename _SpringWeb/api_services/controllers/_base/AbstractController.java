package com.thomasmylonas.spring_mvc_jsf_pf_web_app.servlet_dispatcher_web_components.controllers._base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public abstract class AbstractController implements WebMvcConfigurer {

    protected final Logger LOGGER = LogManager.getLogger(getClass().getName());

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {
        defaultServletHandlerConfigurer.enable();
    }
}
