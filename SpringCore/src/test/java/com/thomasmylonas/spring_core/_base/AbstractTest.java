package com.thomasmylonas.spring_core._base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public abstract class AbstractTest {

    protected void closeApplicationContext(ApplicationContext context) {

        if (context != null) {
            try {
                ((ClassPathXmlApplicationContext) context).close();
            } catch (Exception e) {
                log.error("An error occurred while closing the 'ApplicationContext'");
            }
        }
    }
}
