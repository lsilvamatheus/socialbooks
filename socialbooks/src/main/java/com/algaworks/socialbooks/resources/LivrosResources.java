package com.algaworks.socialbooks.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.service.LivrosService;
import com.algaworks.socialbooks.service.exception.LivroNaoEncontradoException;


@RestController
@RequestMapping("/livros")
public class LivrosResources {
	
	@Autowired
	private LivrosService livrosService;
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Livro>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
	} 
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro) {
		livro = livrosService.salvar(livro);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest() 
				.path("/{id}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value= "/{id}", method= RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		Livro livro = livrosService.buscar(id);
		
		CacheControl cacheControl = CacheControl.maxAge(50, TimeUnit.SECONDS);
		
		return  ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livro);
	}
	
	
	@RequestMapping(value="/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		try {
			livrosService.deletar(id);
		} catch (LivroNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id) {
		livro.setId(id);
		try {
			livrosService.atualizar(livro);
		} catch (LivroNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build(); 
	}
	
	@RequestMapping(value="/{id}/comentarios", method= RequestMethod.POST)
	public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroId, @RequestBody Comentario comentario) {
		
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		
		comentario.setUsuario(auth.getName());
		
		livrosService.salvarComentario(livroId, comentario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();		
	}
	
	
	@RequestMapping(value= "/{id}/comentarios", method= RequestMethod.GET)
	public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable("id") Long livroId){
		
		List<Comentario> comentarios = livrosService.listarComentarios(livroId);
		
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}
	
}