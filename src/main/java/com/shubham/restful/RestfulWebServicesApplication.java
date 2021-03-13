package com.shubham.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class RestfulWebServicesApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestfulWebServicesApplication.class, args);
  }

  @Bean
  public LocaleResolver localeResolver() {
    // If we decide to use the LocaleResolverContext in the controller method then it is
    // better to use AcceptHeaderLocaleResolver class instead of SessionLocaleResolver

    SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
    sessionLocaleResolver.setDefaultLocale(Locale.US);

    return sessionLocaleResolver;
  }

  @Bean
  public ResourceBundleMessageSource messageSource() {
    // We can remove this bean by adding a property in application.properties
    // add spring.messages.basename=messages
    // all the other i18n settings should follow a pattern like "messages_<locale>"

    ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
    resourceBundleMessageSource.setBasename("messages");

    return resourceBundleMessageSource;
  }
}
