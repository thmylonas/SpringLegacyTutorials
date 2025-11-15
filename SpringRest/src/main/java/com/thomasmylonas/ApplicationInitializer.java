package com.thomasmylonas;

import com.thomasmylonas.spring_mvc_jsf_pf_web_app.config.DataAccessConfig;
import com.thomasmylonas.spring_mvc_jsf_pf_web_app.config.RootConfig;
import com.thomasmylonas.spring_mvc_jsf_pf_web_app.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class, DataAccessConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
