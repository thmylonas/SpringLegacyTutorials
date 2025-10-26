package com.thomasmylonas.spring_legacy.spring_dependency_injection;

import com.thomasmylonas.spring_legacy._base.AbstractTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import static org.junit.jupiter.api.Assertions.*;

public class DependencyInjectionByConstructorsTutorialTest extends AbstractTest {

    private DependencyInjectionByConstructorsTutorial dependencyInjectionByConstructorsTutorial;
    private ApplicationContext context;

    @BeforeEach
    void setUp() {

        try {
            context = new ClassPathXmlApplicationContext("config/app_config.xml");
            dependencyInjectionByConstructorsTutorial = (DependencyInjectionByConstructorsTutorial) context.getBean("dependencyInjectionByConstructorsTutorial");
        } catch (BeansException e) {
            LOGGER.error("The bean could not be obtained!");
        }
    }

    @AfterEach
    void tearDown() {
        closeApplicationContext(context);
    }

    @Test
    public void testDiByConstructorsExample1() {
        dependencyInjectionByConstructorsTutorial.diByConstructorsExample1();
    }

    @Test
    public void testDiByConstructorsExample2() {
        dependencyInjectionByConstructorsTutorial.diByConstructorsExample2();
    }

    @Test
    public void testDiByConstructorsExample3() {
        dependencyInjectionByConstructorsTutorial.diByConstructorsExample3();
    }
}
