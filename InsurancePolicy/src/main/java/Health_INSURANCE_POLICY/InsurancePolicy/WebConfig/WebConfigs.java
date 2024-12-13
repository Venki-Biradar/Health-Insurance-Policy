package Health_INSURANCE_POLICY.InsurancePolicy.WebConfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableJpaRepositories(basePackages = "Health_INSURANCE_POLICY.InsurancePolicy.Repository")
@EnableTransactionManagement
public class WebConfigs implements WebMvcConfigurer {

    /**
     * Configure the DataSource bean for database connection.
     */
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver") // Adjust for your DB (e.g., PostgreSQL, Oracle)
                .url("jdbc:mysql://localhost:3306/insurance_policy_db") // Update DB URL
                .username("root") // Update username
                .password("venki#9555") // Update password
                .build();
    }

    /**
     * Configure EntityManagerFactory for JPA.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("Health_INSURANCE_POLICY.InsurancePolicy.InsurancePolicyEntity");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect"); // Adjust for your DB
        jpaProperties.put("hibernate.hbm2ddl.auto", "update"); // Use 'update', 'create-drop', or 'validate' as needed
        jpaProperties.put("hibernate.show_sql", "true"); // Show SQL in logs
        jpaProperties.put("hibernate.format_sql", "true");

        factoryBean.setJpaProperties(jpaProperties);
        return factoryBean;
    }

    /**
     * Configure transaction management.
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    /**
     * Configure View Resolver for JSP.
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        registry.viewResolver(resolver);
    }
}