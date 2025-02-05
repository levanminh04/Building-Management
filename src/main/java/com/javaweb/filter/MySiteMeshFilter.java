package com.javaweb.filter;


import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.webapp.SiteMeshFilter;
import org.springframework.stereotype.Component;

@Component
public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        // Áp dụng decorator cho các đường dẫn
        builder.addDecoratorPath("/admin/*", "admin.jsp")
                .addDecoratorPath("/trang-chu", "web.jsp")
                .addDecoratorPath("/lien-he", "web.jsp")
                .addDecoratorPath("/gioi-thieu", "web.jsp")
                .addDecoratorPath("/tin-tuc", "web.jsp")
                .addDecoratorPath("/san-pham", "web.jsp")
                .addDecoratorPath("/login", "login.jsp")

                // Loại trừ các đường dẫn
                .addExcludedPath("/api/*")
                .addExcludedPath("/errors/*");
    }
}



