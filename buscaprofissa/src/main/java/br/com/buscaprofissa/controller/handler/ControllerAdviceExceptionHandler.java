package br.com.buscaprofissa.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.buscaprofissa.service.exception.LoginOuSenhaIncorretosException;


public class ControllerAdviceExceptionHandler {
	
	@ExceptionHandler(LoginOuSenhaIncorretosException.class)
	public ResponseEntity<String> handleLoginOuSenhaIncorretos(LoginOuSenhaIncorretosException e) {
		return ResponseEntity.badRequest().body("Login ou senha incorretos!");
	}
	
}	
