package br.com.buscaprofissa.service.exception;

public class ErroEnviandoEmailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ErroEnviandoEmailException(String message ) {
		super(message);
	}

}
