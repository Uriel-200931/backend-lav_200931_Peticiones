package com.utxicotepec.lavado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utxicotepec.lavado.model.trabajador;

import java.util.List;



public interface trabajadorRepository extends JpaRepository<trabajador, Long>{
	
	
	List <trabajador> findByNombre(String nombre);
	
}
