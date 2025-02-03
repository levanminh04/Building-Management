package com.javaweb;

import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication // (exclude = { SecurityAutoConfiguration.class })
public class SpringBootWebApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootWebApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
    @PreDestroy
    public void cleanup() {
        AbandonedConnectionCleanupThread.checkedShutdown();
    }
    @Bean
    public FilterRegistrationBean<ErrorPageFilter> disableErrorPageFilter() {
        FilterRegistrationBean<ErrorPageFilter> bean = new FilterRegistrationBean<>(new ErrorPageFilter());
        bean.setEnabled(false);
        return bean;
    }

}
