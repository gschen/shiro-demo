package com.demo.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * Created by apple on 8/18/14.
 */
public class ServletInitializer implements WebApplicationInitializer {
    private static final String MAPPING_URL = "/*";


    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.scan(ClassUtils.getPackageName(getClass()));
        context.scan("com.demo");

        return context;
    }


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = getContext();

        servletContext.addListener(new ContextLoaderListener(context));


        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(MAPPING_URL);

        FilterRegistration.Dynamic springFilter =
                servletContext.addFilter("shiroFilterProxy",
                        DelegatingFilterProxy.class);

        springFilter.setInitParameter("targetBeanName", "shiroFilter");
        springFilter.addMappingForUrlPatterns(null, false, "/");

    }
}
