package com.thomasmylonas.spring_rest.config;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.sql.DataSource;

//import static org.junit.jupiter.api.Assertions.*;

public class DataAccessConfigTest {

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
    public void dataSource() {
        DataSource actual = dataAccessConfig.dataSource();
        Assertions.assertNotNull(actual);
    }
}
