package com.payco.contapayco.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.payco.contapayco.model.Titular;

public interface TitularRepository extends JpaRepository<Titular, Long>{

	public List<Titular> findAllByTitularContainingIgnoreCase(@Param("titular") String titular);

}
