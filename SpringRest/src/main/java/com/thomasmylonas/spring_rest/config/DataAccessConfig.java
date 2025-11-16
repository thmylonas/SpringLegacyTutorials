package com.thomasmylonas.spring_rest.config;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;
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
        emfb.setPackagesToScan("com.thomasmylonas.spring_rest.entities", "com.thomasmylonas.spring_rest.repositories");

        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        //properties.put("hibernate.id.new_generator_mappings", "true");

        emfb.setJpaProperties(properties);
        emfb.afterPropertiesSet();

        return emfb.getObject();
    }

    @Bean(value = "studentdbDatasource")
    public DataSource studentdbDatasource() {

        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("jdbc/studentdb");
        jndiObjectFactoryBean.setResourceRef(true); // Default value: false
        jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
        try {
            jndiObjectFactoryBean.afterPropertiesSet();
        } catch (NamingException e) {
            log.error("NamingException thrown while jndiObjectFactoryBean.afterPropertiesSet");
        }
        return (DataSource) jndiObjectFactoryBean.getObject();
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
