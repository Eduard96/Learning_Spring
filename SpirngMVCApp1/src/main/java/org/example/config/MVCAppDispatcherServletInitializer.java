package org.example.config;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
     Этот class в место web.xml
     Здесь происходит конфигурация сервлета DispatcherServlet
 */
public class MVCAppDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    /**
     * Как и в web.xml указываем путь к конфигурации Spring
     * @return ~applicationContext.xml
     * (В место него у нас Java Class -> MVCAppConfig)
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {MVCAppConfig.class};
    }

    /**
     * Данный метод помогает нам, захватывать все request-ы
     * с помощью DispatcherServlet
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Две нижние функции нужны для того, чтобы обрабатывать такие запросы, которые
     * являются PATCH, DELETE и тд методами
     * @param servletContext
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        registerHiddenFieldFilter(servletContext);
    }

    private void registerHiddenFieldFilter(ServletContext servletContext) {
        servletContext.addFilter("hiddenHttpMethodFilter",
                        new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null, true, "/*");
    }
}
