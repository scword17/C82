package com.conhj.congfig;

import com.conhj.po.UserEntity;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:init.properties")
@EnableTransactionManagement
@ComponentScans(value = {
        @ComponentScan("com.conhj.dao"),
        @ComponentScan("com.conhj.service")
})
public class AppConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("dbcp.driverClass"));
        dataSource.setUrl(env.getProperty("dbcp.url"));
        dataSource.setUsername(env.getProperty("dbcp.username"));
        dataSource.setPassword(env.getProperty("dbcp.password"));
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("dbcp.initialSize")));
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("dbcp.maxActive")));
        dataSource.setMaxIdle(Integer.parseInt(env.getProperty("dbcp.maxIdle")));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("dbcp.minIdle")));
        dataSource.setMaxWait(Integer.parseInt(env.getProperty("dbcp.maxWait")));
        dataSource.setConnectionProperties(env.getProperty("dbcp.connectionProperties"));
        return dataSource;
    }

    @Bean(name = {"sessionFactory"})
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());

        Properties props=new Properties();
        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        props.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        // props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        props.put("hibernate.cache.use_second_level_cache", env.getProperty("hibernate.cache.use_second_level_cache"));
        props.put("hibernate.cache.use_query_cache", env.getProperty("hibernate.cache.use_query_cache"));
        props.put("hibernate.cache.region.factory_class", env.getProperty("hibernate.cache.region.factory_class"));
        props.put("hibernate.cache.provider_configuration_file_resource_path", env.getProperty("hibernate.cache.provider_configuration_file_resource_path"));

        factoryBean.setHibernateProperties(props);

        factoryBean.setAnnotatedClasses(UserEntity.class);
        return factoryBean;
    }
    @Bean
    public HibernateTransactionManager getTransactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
