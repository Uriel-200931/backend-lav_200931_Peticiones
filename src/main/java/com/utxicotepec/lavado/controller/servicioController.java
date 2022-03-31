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
import com.utxicotepec.lavado.model.servicio;
import com.utxicotepec.lavado.repository.servicioRepository;

@RestController                
@RequestMapping("/api")            


public class servicioController {

	@Autowired                
	servicioRepository repServicios;        
	
	@GetMapping("/servicios") 
	
	public ResponseEntity<List<servicio>> getAllServicios (@RequestParam(required =false)String descripcion){
    
		try {              
     List<servicio> servicioo =new ArrayList<servicio>();     
	
     if(descripcion==null)	 
    	 repServicios.findAll().forEach(servicioo::add);  
	 else
		 repServicios.findByDescripcion(descripcion).forEach(servicioo::add); 
		
     if (servicioo.isEmpty()) {   
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);  
		} 
     
     return new ResponseEntity<>(servicioo, HttpStatus.OK);  
    		 
     } catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	} 
     
	}
	
	
	
	
	@GetMapping("/servicios/{idservicio}")
	public ResponseEntity<servicio> getservicioById (@PathVariable("idservicio") Long idservicio){
	Optional<servicio> servicioData =repServicios.findById(idservicio);
	if (servicioData.isPresent()) {
		return new ResponseEntity<>(servicioData.get(), HttpStatus.OK);
	}else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	}
	@PostMapping("/servicios")
	 public ResponseEntity<servicio> createservicio(@RequestBody servicio servicioo){
		 try {
			 servicio  _servicioo= repServicios
					 .save(new servicio(
							 servicioo.getCosto(),
							 servicioo.getDescripcion(),
							 servicioo.getFecha_registro(),
							 servicioo.getStatus()
							 ));
			 return new ResponseEntity<>( _servicioo, HttpStatus.CREATED); 
		 }catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	 }
	  
	 @DeleteMapping("/servicos/{idservicio}")
	 public ResponseEntity<HttpStatus> deleteservicio(@PathVariable("idservicio")Long idservicio){ 
		 try {
			 repServicios.deleteById(idservicio); 
			 return new ResponseEntity<> (HttpStatus.NO_CONTENT);
		 }catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }
	
	 
	 @PutMapping("/servicios/{idservicio}")
	 public ResponseEntity<servicio> updateServicio (@PathVariable("idservicio") Long idservicio, @RequestBody servicio servicioos ){
			Optional<servicio>servicioData =repServicios.findById(idservicio);
			if (servicioData.isPresent()) {
	    	 servicio  _servicioos= servicioData.get();
	    	 _servicioos.setCosto(servicioos.getCosto());
	    	 _servicioos.setDescripcion(servicioos.getDescripcion());
	    	 _servicioos.setFecha_registro(servicioos.getFecha_registro());
	    	 _servicioos.setStatus(servicioos.getStatus());
	 
	    	 
	    	 return new ResponseEntity<>( repServicios.save(_servicioos), HttpStatus.OK);
			 }else{
		
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
		 }
	 
	
}
