package com.utxicotepec.lavado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utxicotepec.lavado.model.venta;



public interface ventaRepository extends JpaRepository<venta, Long>{


	List <venta> findByFecha(String fecha);
}
