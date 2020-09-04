package com.algaworks.socialbooks.domain;

public class DetalhesErro {
	
	private String titulo;
	private String mensagemDesenvolvedor;
	private Long status;
	private Long timestamp;
	
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getMensagemDesenvoledor() {
		return mensagemDesenvolvedor;
	}
	
	public void setMensagemDesenvoledor(String mensagemDesenvoledor) {
		this.mensagemDesenvolvedor = mensagemDesenvoledor;
	}
	
	public Long getStatus() {
		return status;
	}
	
	public void setStatus(Long status) {
		this.status = status;
	}
	
	public Long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
