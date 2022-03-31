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
import com.utxicotepec.lavado.model.trabajador;
import com.utxicotepec.lavado.model.vehiculo;
import com.utxicotepec.lavado.repository.vehiculoRepository;

@RestController               
@RequestMapping("/api")            


public class vehiculoController {

	@Autowired                
	vehiculoRepository repVehiculos;           
	
	@GetMapping("/vehiculos")  
	
	public ResponseEntity<List<vehiculo>> getAllServicios (@RequestParam(required =false)String descripcion){
    
		try {             
     List<vehiculo> vehiculoo =new ArrayList<vehiculo>();    
	
     if(descripcion==null)	
    	 repVehiculos.findAll().forEach(vehiculoo::add);  
	 else
		 repVehiculos.findByMatricula(descripcion).forEach(vehiculoo::add); 
		
     if (vehiculoo.isEmpty()) {  
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);  
		} 
     
     return new ResponseEntity<>(vehiculoo, HttpStatus.OK); 
    		 
     } catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	} 
	}
	
	
	
	@GetMapping("/vehiculos/{idvehiculo}")
	public ResponseEntity<vehiculo> getvehiculoById (@PathVariable("idvehiculo") Long idvehiculo){
	Optional<vehiculo> vehiculoData =repVehiculos.findById(idvehiculo);
	if (vehiculoData.isPresent()) {
		return new ResponseEntity<>(vehiculoData.get(), HttpStatus.OK);
	}else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	}

	@PostMapping("/vehiculos")
	 public ResponseEntity<vehiculo> createtrabajador(@RequestBody vehiculo vehiculoo){
		 try {
			 vehiculo  _vehiculoo= repVehiculos  
					 .save(new vehiculo(  
							 vehiculoo.getColor(),
							 vehiculoo.getIdcliente(),
							 vehiculoo.getMarca(),
							 vehiculoo.getMatricula(),
							 vehiculoo.getModelo(),
							 vehiculoo.getTipo(),
							 vehiculoo.getFecha_registro(),
							 vehiculoo.getStatus()								
							 ));
			 return new ResponseEntity<>( _vehiculoo, HttpStatus.CREATED); 
		 }catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	 }

	 @DeleteMapping("/vehiculos/{idvehiculo}")
	 public ResponseEntity<HttpStatus> deletetrabajador(@PathVariable("idvehiculo")Long idvehiculo){
		 try {
			 repVehiculos.deleteById(idvehiculo);
			 return new ResponseEntity<> (HttpStatus.NO_CONTENT);
		 }catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }
	
	
	 
	 @PutMapping("/vehiculos/{idvehiculo}")
	 public ResponseEntity<vehiculo> updateVehiculo (@PathVariable("idvehiculo") Long idvehiculo, @RequestBody vehiculo vehiculooo ){
			Optional<vehiculo> vehiculoData =repVehiculos.findById(idvehiculo);
			if (vehiculoData.isPresent()) {
				vehiculo _vehiculooo= vehiculoData.get();
	    	 _vehiculooo.setColor(vehiculooo.getColor());
	    	 _vehiculooo.setIdcliente(vehiculooo.getIdcliente());
	    	 _vehiculooo.setMarca(vehiculooo.getMarca());
	    	 _vehiculooo.setMatricula(vehiculooo.getMatricula());
	    	 _vehiculooo.setModelo(vehiculooo.getModelo());
	    	 _vehiculooo.setTipo(vehiculooo.getTipo());
	    	 _vehiculooo.setFecha_registro(vehiculooo.getFecha_registro());
	    	 _vehiculooo.setStatus(vehiculooo.getStatus());
	    	 
	    	 return new ResponseEntity<>( repVehiculos.save(_vehiculooo), HttpStatus.OK);
			 }else{
		
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
		 }
	 
	
	 
	 
	 
}
