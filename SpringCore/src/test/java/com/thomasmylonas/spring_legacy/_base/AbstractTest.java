package com.thomasmylonas.spring_legacy._base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class AbstractTest {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class.getSimpleName());

    protected void closeApplicationContext(ApplicationContext context) {

        if (context != null) {
            try {
                ((ClassPathXmlApplicationContext) context).close();
            } catch (Exception e) {
                LOGGER.error("An error occurred while closing the 'ApplicationContext'");
            }
        }
    }
}
