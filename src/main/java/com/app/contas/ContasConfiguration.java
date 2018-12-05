package com.app.contas;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class ContasConfiguration extends WebMvcConfigurerAdapter {


	@Bean
	public LocaleResolver localeResolver() {
		
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}

// Acho que serve para quando utilizar internacionaliazação o "lang" seria um parametro na url ?lang="pt_BR"
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//        lci.setParamName("lang");
//        return lci;
//    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
	
    
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        
    	ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages/messages");
        messageSource.setDefaultEncoding("UTF-8");
        System.out.println("HASH DO MESSAGE SOURCE: " + messageSource.hashCode());
        return messageSource;
    }
}
