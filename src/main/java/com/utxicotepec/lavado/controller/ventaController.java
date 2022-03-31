package com.utxicotepec.lavado.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utxicotepec.lavado.model.cliente;
import com.utxicotepec.lavado.model.vehiculo;
import com.utxicotepec.lavado.model.venta;
import com.utxicotepec.lavado.repository.ventaRepository;


@RestController                
@RequestMapping("/api")             

public class ventaController {

	@Autowired               
	ventaRepository repVentas;            
	
	@GetMapping("/ventas") 
	
	public ResponseEntity<List<venta>> getAllServicios (@RequestParam(required =false)String fecha){
    
		try {            
     List<venta> ventaa =new ArrayList<venta>();     
	
     if(fecha==null)	 
    	 repVentas.findAll().forEach(ventaa::add);  
	 else
		 repVentas.findByFecha(fecha).forEach(ventaa::add); 
		
     if (ventaa.isEmpty()) {   
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT); 
		} 
     
     return new ResponseEntity<>(ventaa, HttpStatus.OK); 
    		 
     } catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	} 
	}
	

	@GetMapping("/ventas/{idventa}")
	public ResponseEntity<venta> getventaById (@PathVariable("idventa") Long idventa){
	Optional<venta> ventaData =repVentas.findById(idventa);
	if (ventaData.isPresent()) {
		return new ResponseEntity<>(ventaData.get(), HttpStatus.OK);
	}else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	}
	
	
	
	@PostMapping("/ventas")
	 public ResponseEntity<venta> createvenyta(@RequestBody venta ventaa){
		 try {
			 venta  _ventaa= repVentas  
					 .save(new venta( 
							 ventaa.getFecha(),
							 ventaa.getIdcajero(),
							 ventaa.getIdservicio(),
							 ventaa.getIdtrabajador(),
							 ventaa.getIdvehiculo()
							 ));
			 return new ResponseEntity<>( _ventaa, HttpStatus.CREATED); 
		 }catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	 }

	 @DeleteMapping("/ventas/{idventa}")
	 public ResponseEntity<HttpStatus> deletetrabajador(@PathVariable("idventa")Long idventa){
		 try {
			 repVentas.deleteById(idventa); 
			 return new ResponseEntity<> (HttpStatus.NO_CONTENT);
		 }catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }
	
	
	 @PutMapping("/ventas/{idventa}")
	 public ResponseEntity<venta> updateCliente (@PathVariable("idventa") Long idventa, @RequestBody venta ventaaaa ){
			Optional<venta> ventaData =repVentas.findById(idventa);
			if (ventaData.isPresent()) {
				venta _ventaaaa= ventaData.get();
				_ventaaaa.setFecha(ventaaaa.getFecha());
				_ventaaaa.setIdcajero(ventaaaa.getIdcajero());
	    	 _ventaaaa.setIdservicio(ventaaaa.getIdservicio());
	    	 _ventaaaa.setIdtrabajador(ventaaaa.getIdtrabajador());
	    	 _ventaaaa.setIdvehiculo(ventaaaa.getIdvehiculo());

	    	 
	    	 return new ResponseEntity<>( repVentas.save(_ventaaaa), HttpStatus.OK);
			 }else{
		
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
		 }
	
}
