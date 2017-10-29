package br.com.buscaprofissa.service.exception;

public class CpfInvalidoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CpfInvalidoException(String message) {
		super(message);
	}
	
}
