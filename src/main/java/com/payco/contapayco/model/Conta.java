package com.payco.contapayco.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_contas")
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@NotBlank(message = "O atributo nome é obrigatório!") 
	private String nome;
	
	private float saldo;
	
	@ManyToOne
	@JsonIgnoreProperties("contas")
	private Titular titular;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	
	
	

}
