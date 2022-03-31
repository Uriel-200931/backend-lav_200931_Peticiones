package com.utxicotepec.lavado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utxicotepec.lavado.model.servicio;
import java.util.List;


public interface servicioRepository extends JpaRepository< servicio, Long>{


	List <servicio> findByDescripcion(String descripcion);
	
	
}
