package com.thomasmylonas.spring_core.spring_dependency_injection;

import com.thomasmylonas.spring_core._base.AbstractTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class DependencyInjectionBySettersTutorialTest extends AbstractTest {

    private DependencyInjectionBySettersTutorial dependencyInjectionBySettersTutorial;
    private ApplicationContext context;

    @BeforeEach
    void setUp() {

        try {
            context = new ClassPathXmlApplicationContext("config/app_config.xml");
            dependencyInjectionBySettersTutorial = (DependencyInjectionBySettersTutorial) context.getBean("dependencyInjectionBySettersTutorial");
        } catch (BeansException e) {
            log.error("The bean could not be obtained!");
        }
    }

    @AfterEach
    void tearDown() {
        closeApplicationContext(context);
    }

    @Test
    public void testDiBySettersExample1() {
        dependencyInjectionBySettersTutorial.diBySettersExample1();
    }

    @Test
    public void testDiBySettersExample2() {
        dependencyInjectionBySettersTutorial.diBySettersExample2();
    }

    @Test
    public void testDiBySettersExample3() {
        dependencyInjectionBySettersTutorial.diBySettersExample3();
    }
}
