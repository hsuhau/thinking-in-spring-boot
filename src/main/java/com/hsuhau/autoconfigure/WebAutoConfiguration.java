package com.hsuhau.autoconfigure;

import com.hsuhau.config.WebConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 24. Externalized Configuration
 * Spring Boot allows you to externalize your configuration so you can work with the same application code in different environments. You can use properties files, YAML files, environment variables and command-line arguments to externalize configuration. Property values can be injected directly into your beans using the @Value annotation, accessed via Spring’s Environment abstraction or bound to structured objects via @ConfigurationProperties.
 *
 * Spring Boot uses a very particular PropertySource order that is designed to allow sensible overriding of values. Properties are considered in the following order:
 *
 * Devtools global settings properties on your home directory (~/.spring-boot-devtools.properties when devtools is active).
 * @TestPropertySource annotations on your tests.
 * @SpringBootTest#properties annotation attribute on your tests.
 * Command line arguments.
 * Properties from SPRING_APPLICATION_JSON (inline JSON embedded in an environment variable or system property)
 * ServletConfig init parameters.
 * ServletContext init parameters.
 * JNDI attributes from java:comp/env.
 * Java System properties (System.getProperties()).
 * OS environment variables.
 * A RandomValuePropertySource that only has properties in random.*.
 * Profile-specific application properties outside of your packaged jar (application-{profile}.properties and YAML variants)
 * Profile-specific application properties packaged inside your jar (application-{profile}.properties and YAML variants)
 * Application properties outside of your packaged jar (application.properties and YAML variants).
 * Application properties packaged inside your jar (application.properties and YAML variants).
 * @PropertySource annotations on your @Configuration classes.
 * Default properties (specified using SpringApplication.setDefaultProperties).
 */
@ConditionalOnWebApplication
@Configuration
@Import(WebConfiguration.class)
public class WebAutoConfiguration {

}
