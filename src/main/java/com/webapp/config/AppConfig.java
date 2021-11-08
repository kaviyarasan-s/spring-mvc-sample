package com.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import com.webapp.interceptor.AppInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan({ "com.webapp" })
public class AppConfig implements WebMvcConfigurer {

	@Autowired
	ApplicationContext applicationContext;

	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println(" inside interceptor ");
		registry.addInterceptor(new AppInterceptor());
	}

	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public InternalResourceViewResolver createJSPViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/jsp/");
		viewResolver.setViewNames("*.jsp");
		viewResolver.setOrder(2);
		return viewResolver;
	}

	@Bean
	public ThymeleafViewResolver createThymeleafViewResolver() {
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setTemplateEngine(getSpringTemplateEngine());
		thymeleafViewResolver.setViewNames(new String[] { "*.html" });
		thymeleafViewResolver.setExcludedViewNames(new String[] { "*.jsp" });
		thymeleafViewResolver.setOrder(1);
		return thymeleafViewResolver;
	}

	@Bean
	public SpringTemplateEngine getSpringTemplateEngine() {
		SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
		springTemplateEngine.setTemplateResolver(getThymeleafTemplateResolver());
		springTemplateEngine.setEnableSpringELCompiler(true);
		return springTemplateEngine;
	}

	@Bean
	public SpringResourceTemplateResolver getThymeleafTemplateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/views/html/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCacheable(false);
		templateResolver.setApplicationContext(applicationContext);
		templateResolver.setOrder(1);
		return templateResolver;
	}

}
