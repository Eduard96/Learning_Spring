package org.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
    Этот class в место applicationContext.xml
    Здесь происходит обычная Spring конфигурация
 */
@Configuration
@ComponentScan("org.example")
@PropertySource("classpath:db.properties")
@EnableJpaRepositories(basePackages = "org.example.repository")
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

//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
//        factoryBean.setDataSource(dataSource());
//        factoryBean.setPackagesToScan("org.example.model");
//        return factoryBean;
//    }

    @Bean
    public Properties additionalProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        //properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.format sql", true);
        properties.put("hibernate.show_sql", true);
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("org.example.model");
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaProperties(additionalProperties());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor postProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}