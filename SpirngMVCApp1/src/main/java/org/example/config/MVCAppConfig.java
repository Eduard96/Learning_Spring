package org.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.sql.DataSource;

/**
    Этот class в место applicationContext.xml
    Здесь происходит обычная Spring конфигурация
 */
@Configuration
@ComponentScan("org.example")
@PropertySource("classpath:db.properties")
@EnableWebMvc
public class MVCAppConfig implements WebMvcConfigurer {
    /**
     * Данный интерфейст реализуется,
     * если мы настраиваем под свои цели/нужды Spring MVC
     * В данном случаем мы использовали, чтобы изменить шаблонизатор,
     * то есть инструмент работы с .html файлами, на
     * Thymeleaf.
     * И для этих целей мы переопределили метод - configureViewResolvers()

     ---------------------------------------------------------------------
     * Обычное внедрение зависимости
     * В данном случае Spring внедрит applicationContext
     */
    @Autowired
    private Environment env;

    private final ApplicationContext applicationContext;

    @Autowired
    public MVCAppConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * applicationContext используем для конфигурации
     * в данном случае указываем путь к нашим представлениям
     * (то есть где находится код, который будет "показан" пользвателю)
     * Так же указываем рассширение данных представлений
     * @return
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("WEB-INF/views/");
        resolver.setSuffix(".html");
        return resolver;
    }

    /**
     * Опять какая-то конфигурация для представлений,
     * но не углубился
     * @return
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        engine.setEnableSpringELCompiler(true);
        return engine;
    }

    /**
     * Здесь мы указываем что будем использовать Thymeleaf
     * Данный метод переопределяем из интерфейса WebMvcConfigurer
     * @param viewResolverRegistry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        viewResolverRegistry.viewResolver(resolver);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManager = new DriverManagerDataSource();

        driverManager.setDriverClassName(env.getRequiredProperty("driver"));
        driverManager.setUrl(env.getRequiredProperty("url"));
        driverManager.setUsername(env.getRequiredProperty("username"));
        driverManager.setPassword(env.getRequiredProperty("password"));

        return driverManager;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}