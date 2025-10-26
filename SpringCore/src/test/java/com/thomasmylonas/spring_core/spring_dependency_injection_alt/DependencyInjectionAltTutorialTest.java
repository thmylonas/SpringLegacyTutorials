package com.thomasmylonas.spring_core.spring_dependency_injection_alt;

import com.thomasmylonas.spring_core._base.AbstractTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import static org.junit.jupiter.api.Assertions.*;

public class DependencyInjectionAltTutorialTest extends AbstractTest {

    private DependencyInjectionAltTutorial dependencyInjectionAltTutorial;
    private ApplicationContext context;

    @BeforeEach
    void setUp() {

        try {
            context = new ClassPathXmlApplicationContext("config/app_config.xml");
            dependencyInjectionAltTutorial = (DependencyInjectionAltTutorial) context.getBean("dependencyInjectionAltTutorial");
        } catch (BeansException e) {
            LOGGER.error("The bean could not be obtained!");
        }
    }

    @AfterEach
    void tearDown() {
        closeApplicationContext(context);
    }

    @Test
    public void testTutorial() {
        dependencyInjectionAltTutorial.tutorial();
    }
}
