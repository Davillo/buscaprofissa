package br.com.buscaprofissa.service.exception;

public class EmailNaoCadastradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmailNaoCadastradoException(String message) {
		super(message);
	}

}
