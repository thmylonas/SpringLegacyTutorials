package com.thomasmylonas.spring_core.spring_dependency_injection_alt;

import com.thomasmylonas.spring_core._base.AbstractSpringTutorials;
import com.thomasmylonas.spring_core.spring_dependency_injection_alt._models.Student;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyInjectionAltTutorial extends AbstractSpringTutorials {

    private static final String DI_ALT_CONFIG = "config/spring_dependency_injection_alt_config/di_alt_config.xml";

    public void tutorial() {

        try (var context = new ClassPathXmlApplicationContext(DI_ALT_CONFIG)) {

            Student studentThomas = (Student) context.getBean("studentThomas");
            LOGGER.info("{}", studentThomas);
        } catch (BeansException e) {
            LOGGER.error("The bean could not be obtained!");
        }
    }
}
