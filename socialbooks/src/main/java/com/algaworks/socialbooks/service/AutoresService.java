package com.algaworks.socialbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.repository.AutoresRepository;
import com.algaworks.socialbooks.service.exception.AutorExistenteException;
import com.algaworks.socialbooks.service.exception.AutorNaoEncontradoException;

@Service
public class AutoresService {

	@Autowired
	private AutoresRepository autoresRepository;

	public List<Autor> listar() {
		return autoresRepository.findAll();
	}

	public Autor salvar(Autor autor) {
		if(autor.getId()!= null) {
			Autor a = autoresRepository.findOne(autor.getId());
			if(a != null) {
			 throw new AutorExistenteException("O autor já existe");
			}	
		}
		return autoresRepository.save(autor);
		
	}
 
	public Autor buscar(Long autorId) {

		Autor autor = autoresRepository.findOne(autorId);
		  if(autor == null) throw new AutorNaoEncontradoException("Não foi possivel encontrar o autor!");
		
		return autor;
	}
	
	public void deletar(Long autorId) {
		try {
			autoresRepository.delete(autorId);
		} catch (EmptyResultDataAccessException e) {
			throw new AutorNaoEncontradoException("Não foi possivel encontrar o autor!");
		}
	}
	
	public void atualizar(Autor autor) {
		verificaExistencia(autor);
		autoresRepository.save(autor);
		
	}
	
	public void verificaExistencia(Autor autor) {
		buscar(autor.getId());
	}
}
