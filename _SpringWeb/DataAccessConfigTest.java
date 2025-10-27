package com.thomasmylonas.spring_mvc_jsf_pf_web_app.config;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.sql.DataSource;

class DataAccessConfigTest {

    private DataAccessConfig dataAccessConfig;

    @BeforeEach
    void setUp() {
        dataAccessConfig = new DataAccessConfig();
    }

    @AfterEach
    void tearDown() {
        dataAccessConfig = null;
    }

    @Test
    void dataSource() {
        DataSource actual = dataAccessConfig.dataSource();
        Assertions.assertNotNull(actual);
    }
}
