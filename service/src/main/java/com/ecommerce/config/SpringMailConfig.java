/*
 Copyright (c) 2020. Semicolon Africa
 312 Herbert Macaulay Way, Yaba, Lagos.

 Project Name: lamp
 Class Name: com.lamp.config.SpringMailConfig
 File Name: SpringMailConfig.java
 File Path: /home/scv2003/IdeaProjects/lampOnboarding/service/src/main/java/com/lamp/config/SpringMailConfig.java
 Author:  scv2003
 Last Modified: 01/05/2020, 4:33 PM.

 The contents of this file and project are not available to the public.

 */

package com.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.Properties;

@Configuration
public class SpringMailConfig {
	private static final String PASSWORD = "mailgunapikey";
	private static final String USER_NAME = "mail gun api username";
	private static final String HOST = "smtp.mailgun.org";
	private static final int PORT = 587;
	private static final boolean SSL_FLAG = true;

	@Bean
	public JavaMailSender mailSender() {

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost(HOST); //change or read from properties
		mailSender.setPort(PORT);
		mailSender.setProtocol("smtp");
		mailSender.setUsername(USER_NAME);
		mailSender.setPassword(PASSWORD);


		// create java mail properties
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", true);
		mailProperties.put("mail.smtp.starttls.enable", true);
		mailProperties.put("mail.smtp.timeout", 5000);
		mailProperties.put("mail.smtp.connectiontimeout", 5000);
		mailSender.setJavaMailProperties(mailProperties);

		return mailSender;

	}

	@Bean(name = "textTemplateEngine")
	public TemplateEngine textTemplateEngine() {
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.addTemplateResolver(textTemplateResolver());
		return templateEngine;
	}

	private ITemplateResolver textTemplateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("templates/text/");
		templateResolver.setSuffix(".txt");
		templateResolver.setTemplateMode(TemplateMode.TEXT);
		templateResolver.setCharacterEncoding("UTF8");
		templateResolver.setCheckExistence(true);
		templateResolver.setCacheable(false);
		templateResolver.setOrder(Integer.valueOf(1));
		return templateResolver;
	}

	private ITemplateResolver htmlTemplateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("classpath:templates/html/");
		templateResolver.setSuffix(".html");
		templateResolver.setOrder(Integer.valueOf(2));
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding("UTF8");
		templateResolver.setCheckExistence(true);
		templateResolver.setCacheable(false);
		return templateResolver;
	}


	private ITemplateResolver fileTemplateResolver() {
		FileTemplateResolver templateResolver = new FileTemplateResolver();
		templateResolver.setPrefix("classpath:templates/html/"); //Change based on your environment
		templateResolver.setSuffix(".html");
		templateResolver.setOrder(Integer.valueOf(3));
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding("UTF8");
		templateResolver.setCheckExistence(true);
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	@Bean(name = "htmlTemplateEngine")
	public TemplateEngine htmlTemplateEngine() {
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.addTemplateResolver(htmlTemplateResolver());
		return templateEngine;
	}

	private ITemplateResolver stringTemplateResolver() {
		final StringTemplateResolver templateResolver = new StringTemplateResolver();
		templateResolver.setTemplateMode("HTML");
		templateResolver.setCacheable(false);
		templateResolver.setOrder(Integer.valueOf(4));
		return templateResolver;
	}

	@Bean(name = "fileTemplateEngine")
	public TemplateEngine fileTemplateEngine() {
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.addTemplateResolver(fileTemplateResolver());
		return templateEngine;
	}

	@Bean
	public ResourceBundleMessageSource emailMessageSource() {
		final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("classpath:templates/html");
		return messageSource;
	}

	@Bean("emailTemplateEngine")
	public TemplateEngine emailTemplateEngine() {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		// Resolver for TEXT emails
		templateEngine.addTemplateResolver(textTemplateResolver());
		// Resolver for HTML emails (except the editable one)
		templateEngine.addTemplateResolver(htmlTemplateResolver());
		// Resolver for HTML editable emails (which will be treated as a String)
		templateEngine.addTemplateResolver(stringTemplateResolver());
		// Message source, internationalization specific to emails
		templateEngine.setTemplateEngineMessageSource(emailMessageSource());
		return templateEngine;
	}

}
