package ru.sberbank.bankapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

@Configuration
public class AppConfig {
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory(){
        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("ru.sberbank.bankapi");
        return factoryBean;
    }
}
