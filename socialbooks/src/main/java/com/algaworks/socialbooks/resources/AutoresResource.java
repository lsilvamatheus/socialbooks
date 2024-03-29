package com.algaworks.socialbooks.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.service.AutoresService;

@RestController
@RequestMapping(value = "/autores")
public class AutoresResource {

	@Autowired
	private AutoresService autoresService;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, produces=
				{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Autor>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(autoresService.listar());
	}

	@CrossOrigin
	@RequestMapping(method= RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Autor autor) {
		autor = autoresService.salvar(autor);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value= "/{id}", method= RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long autorId) {
		return ResponseEntity.status(HttpStatus.OK).body(autoresService.buscar(autorId));
	}

	@CrossOrigin
	@RequestMapping(value= "/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long autorId) {
		autoresService.deletar(autorId);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value= "/{id}", method= RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Autor autor, @PathVariable("id") Long autorId) {
		autor.setId(autorId);
		autoresService.atualizar(autor);
		return ResponseEntity.noContent().build();
	}

}
