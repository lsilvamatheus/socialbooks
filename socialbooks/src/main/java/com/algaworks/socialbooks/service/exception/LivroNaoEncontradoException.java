package com.algaworks.socialbooks.service.exception;

public class LivroNaoEncontradoException extends RuntimeException {
	
	public LivroNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public LivroNaoEncontradoException(String mensagem, Exception causa) {
		super(mensagem, causa);
	}

}
