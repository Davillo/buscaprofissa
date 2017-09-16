package br.com.buscaprofissa.service.exception;

public class LoginOuSenhaIncorretosException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LoginOuSenhaIncorretosException(String message) {
		super(message);
	}

}
