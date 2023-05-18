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

import com.payco.contapayco.model.Conta;
import com.payco.contapayco.repository.ContaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contas")
@CrossOrigin("*")
public class ContaController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@GetMapping
	public ResponseEntity<List<Conta>> getAll(){
		return ResponseEntity.ok(contaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Conta> getById(@PathVariable Long id){
		return contaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Conta>> getByTitulo(@PathVariable String nome){
		return ResponseEntity.ok(contaRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Conta> post(@Valid @RequestBody Conta conta){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(contaRepository.save(conta));
	}
	
	@PutMapping
	public ResponseEntity<Conta> put(@Valid @RequestBody Conta conta){
		return contaRepository.findById(conta.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
						.body(contaRepository.save(conta)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
				
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Conta> conta = contaRepository.findById(id);
		
		if(conta.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		contaRepository.deleteById(id);
	}

}
