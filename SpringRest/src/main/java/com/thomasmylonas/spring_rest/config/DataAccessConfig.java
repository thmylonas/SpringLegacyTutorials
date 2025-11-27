package com.thomasmylonas.spring_rest.config;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.thomasmylonas.spring_rest.entities", "com.thomasmylonas.spring_rest.daos"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
        }
)
@Slf4j
public class DataAccessConfig {

    @Bean(value = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean(value = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {

        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();

        //emfb.setPersistenceUnitName("StudentPU_H2");
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        emfb.setPackagesToScan("com.thomasmylonas.spring_rest.entities", "com.thomasmylonas.spring_rest.daos");

        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "false");
        //properties.put("hibernate.id.new_generator_mappings", "true");

        emfb.setJpaProperties(properties);
        emfb.afterPropertiesSet();

        return emfb.getObject();
    }

    @Bean(value = "studentdbDatasource")
    public DataSource studentdbDatasource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource(); // Alternatives: "org.springframework.jdbc.datasource.SimpleDriverDataSource", "org.springframework.jdbc.datasource.SingleConnectionDataSource"
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/studentdb;AUTO_SERVER=TRUE;DB_CLOSE_DELAY=-1"); // "jdbc:h2:mem:studentdb"
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    //@Bean(value = "oracledbDatasource")
    public DataSource oracledbDatasource() {

        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("jdbc/oracledb");
        jndiObjectFactoryBean.setResourceRef(true); // Default value: false
        jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
        try {
            jndiObjectFactoryBean.afterPropertiesSet();
        } catch (NamingException e) {
            log.error("NamingException thrown while jndiObjectFactoryBean.afterPropertiesSet");
        }
        return (DataSource) jndiObjectFactoryBean.getObject();
    }

    @Bean(value = "jpaH2VendorAdapter")
    public JpaVendorAdapter jpaH2VendorAdapter() {

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        return adapter;
    }

    //@Bean(value = "jpaOracleVendorAdapter")
    public JpaVendorAdapter jpaOracleVendorAdapter() {

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.ORACLE);
        adapter.setDatabasePlatform("org.hibernate.dialect.Oracle12cDialect");
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        return adapter;
    }
}
