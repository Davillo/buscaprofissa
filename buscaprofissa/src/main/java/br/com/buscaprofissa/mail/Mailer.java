package br.com.buscaprofissa.mail;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Mailer {
	
	@Async
	public void enviar() {
		try {
			Thread.sleep(10000);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
