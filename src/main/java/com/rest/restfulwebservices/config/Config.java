package com.rest.restfulwebservices.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


@SuppressWarnings("deprecation")
@Configuration
public class Config extends WebMvcConfigurerAdapter {
   @Bean
   public LocaleResolver localeResolver() {
      AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
      localeResolver.setDefaultLocale(Locale.US);
      return localeResolver;
   }
   
// the below code can be subtituted by writing the basename in application.properties file 
//   @Bean 
//	public ResourceBundleMessageSource messageSource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		
//		messageSource.setBasename("messages");
//		return messageSource;
//	}
//   
}