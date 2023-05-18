package com.payco.contapayco.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.payco.contapayco.model.Titular;
import com.payco.contapayco.repository.TitularRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/titulares")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TitularController {
	

	@Autowired
	private TitularRepository titularRepository;
	
	@GetMapping
	public ResponseEntity<List<Titular>> getAll(){
		return ResponseEntity.ok(titularRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Titular> getById(@PathVariable Long id){
		return titularRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/titular/{titular}")
	public ResponseEntity<List<Titular>> getByTitular(@PathVariable String titular){
		return ResponseEntity.ok(titularRepository.findAllByTitularContainingIgnoreCase(titular));
	}
	
	@PostMapping
	public ResponseEntity<Titular> post(@Valid @RequestBody Titular titular){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(titularRepository.save(titular));
	}
	
	@PutMapping
	public ResponseEntity<Titular> put(@Valid @RequestBody Titular titular){
		return titularRepository.findById(titular.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
				.body(titularRepository.save(titular)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Titular> titular = titularRepository.findById(id);
		
		if(titular.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		titularRepository.deleteById(id);
	}

}
