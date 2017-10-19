package br.com.buscaprofissa.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import br.com.buscaprofissa.mail.Mailer;

@Configuration
@ComponentScan(basePackageClasses = Mailer.class)
public class MailConfig {

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.sendgrid.net");
		mailSender.setPort(587);
		mailSender.setUsername("innovatech");
		mailSender.setPassword("somosads316");
		
		
		Properties props = new Properties();
		props.put("mail.transport.protocol", true);
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.debug", false);
		props.put("mail.smtp.connectiontimeout", 10000); // milisegundos
		
		mailSender.setJavaMailProperties(props);
		
		return mailSender;
	}
	
}
