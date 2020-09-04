package com.algaworks.socialbooks.service.exception;

public class AutorNaoEncontradoException extends RuntimeException {
	
	public AutorNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public AutorNaoEncontradoException(String mensagem, Exception causa ) {
		super(mensagem, causa);
	}
}
