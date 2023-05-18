package com.payco.contapayco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.payco.contapayco.model.Conta;


public interface ContaRepository extends JpaRepository<Conta, Long>{
	
	public List <Conta> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

}

