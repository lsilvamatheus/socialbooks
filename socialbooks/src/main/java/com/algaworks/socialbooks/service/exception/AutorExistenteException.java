package com.algaworks.socialbooks.service.exception;

public class AutorExistenteException extends RuntimeException {
	
	public AutorExistenteException(String mensagem) {
		super(mensagem);
	}
	
	public AutorExistenteException(String mensagem, Exception causa) {
		super(mensagem, causa);
	}

}
