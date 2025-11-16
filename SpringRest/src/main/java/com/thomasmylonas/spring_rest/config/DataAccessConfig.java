package com.thomasmylonas.spring_rest.config;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.thomasmylonas.spring_rest.entities", "com.thomasmylonas.spring_rest.repositories"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
        }
)
@EnableJpaRepositories(basePackages = "com.thomasmylonas.spring_rest.repositories")
@EnableTransactionManagement
public class DataAccessConfig {

    private final Logger LOGGER = LogManager.getLogger(getClass().getName());

    @Bean(value = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean(value = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {

        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();

        //emfb.setPersistenceUnitName("School_PU");
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        emfb.setPackagesToScan("com.thomasmylonas.spring_mvc_jsf_pf_web_app.data_access_layer");

        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        //properties.put("hibernate.id.new_generator_mappings", "true");

        emfb.setJpaProperties(properties);
        emfb.afterPropertiesSet();

        return emfb.getObject();
    }

    @Bean(value = "datasource")
    public DataSource datasource() {

        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("jdbc/oracledb");
        jndiObjectFactoryBean.setResourceRef(true); // Default value: false
        jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
        try {
            jndiObjectFactoryBean.afterPropertiesSet();
        } catch (NamingException e) {
            LOGGER.error("NamingException thrown while jndiObjectFactoryBean.afterPropertiesSet");
        }
        return (DataSource) jndiObjectFactoryBean.getObject();
    }

    @Bean(value = "jpaVendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter() {

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.ORACLE);
        adapter.setDatabasePlatform("org.hibernate.dialect.Oracle12cDialect");
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        return adapter;
    }
}
