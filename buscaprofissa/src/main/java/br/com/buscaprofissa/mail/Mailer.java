package br.com.buscaprofissa.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.security.UsuarioLogado;

@Component
public class Mailer {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Async
	public void enviar(Usuario usuario, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
			SimpleMailMessage mensagem = new SimpleMailMessage();
			mensagem.setFrom("startupinnovatech@gmail.com");
			mensagem.setTo(usuario.getEmail());
			mensagem.setSubject("Solicitação de serviço no BuscaProfissa!");
			mensagem.setText("Olá , " + usuario.getNome() + " o usuário "+usuarioLogado.getUsuario().getNome()+ " Solicita um serviço! \n"
	
					);
			mailSender.send(mensagem);
	}
	
}
