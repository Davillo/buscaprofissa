package br.com.buscaprofissa.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.buscaprofissa.model.Usuario;

@Component
public class Mailer {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Async
	public void enviar() {
			SimpleMailMessage mensagem = new SimpleMailMessage();
			mensagem.setFrom("startupinnovatech@gmail.com");
			mensagem.setTo("davilloaurelio@hotmail.com");
			mensagem.setSubject("Teste de e-mail");
			mensagem.setText("Apenas um teste com sendgrid");
			
			mailSender.send(mensagem);
	}
	
}
