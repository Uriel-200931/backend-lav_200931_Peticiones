package com.utxicotepec.lavado.controller;

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

import com.utxicotepec.lavado.model.servicio;
import com.utxicotepec.lavado.model.trabajador;
import com.utxicotepec.lavado.repository.trabajadorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController               
@RequestMapping("/api")             

public class trabajadorController {

	@Autowired                
	trabajadorRepository repTrabajadores;            
	
	@GetMapping("/trabajadores")  
	
	public ResponseEntity<List<trabajador>> getAllTrabajadores (@RequestParam(required =false)String nombre){
    
		try {              
     List<trabajador> trabajadorr =new ArrayList<trabajador>();    
	
     if(nombre==null)	 
    	 repTrabajadores.findAll().forEach(trabajadorr::add); 
	 else
		 repTrabajadores.findByNombre(nombre).forEach(trabajadorr::add); 
		
     if (trabajadorr.isEmpty()) {   
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);  
		} 
     
     return new ResponseEntity<>(trabajadorr, HttpStatus.OK);  
    		 
     } catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	} 
     
	}

	@GetMapping("/trabajadores/{idtrabajador}")
	public ResponseEntity<trabajador> gettrabajadorById (@PathVariable("idtrabajador") Long idtrabajador){
	Optional<trabajador> trabajadorData =repTrabajadores.findById(idtrabajador);
	if (trabajadorData.isPresent()) {
		return new ResponseEntity<>(trabajadorData.get(), HttpStatus.OK);
	}else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	}

	@PostMapping("/trabajadores")
	 public ResponseEntity<trabajador> createtrabajador(@RequestBody trabajador trabajadorrr){
		 try {
			 trabajador  _trabajadorrr= repTrabajadores  
					 .save(new trabajador(  
							 trabajadorrr.getNombre(),
							 trabajadorrr.getAmaterno(),  
							 trabajadorrr.getApaterno(),
							 trabajadorrr.getDireccion(),
							 trabajadorrr.getTelefono(),
							 trabajadorrr.getCorreo(),
							 trabajadorrr.getFecha_registro(),
							 trabajadorrr.getStatus()
							 ));
			 return new ResponseEntity<>( _trabajadorrr, HttpStatus.CREATED);
		 }catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	 }	 
	
	 @DeleteMapping("/trabajadores/{idtrabajador}")
	 public ResponseEntity<HttpStatus> deletetrabajador(@PathVariable("idtrabajador")Long idtrabajador){ 
		 try {
			 repTrabajadores.deleteById(idtrabajador); 
			 return new ResponseEntity<> (HttpStatus.NO_CONTENT);
		 }catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }
	
	
	 @PutMapping("/trabajadores/{idtrabajador}")
	 public ResponseEntity<trabajador> updateTrabajador (@PathVariable("idtrabajador") Long idtrabajador, @RequestBody trabajador trabajadorr ){
			Optional<trabajador>trabajadorData =repTrabajadores.findById(idtrabajador);
			if (trabajadorData.isPresent()) {
				trabajador  _trabajadorr= trabajadorData.get();
				_trabajadorr.setNombre(trabajadorr.getNombre());
				_trabajadorr.setAmaterno(trabajadorr.getAmaterno());
				_trabajadorr.setApaterno(trabajadorr.getApaterno());
				_trabajadorr.setDireccion(trabajadorr.getDireccion());
				_trabajadorr.setCorreo(trabajadorr.getCorreo());
				_trabajadorr.setTelefono(trabajadorr.getTelefono());
				_trabajadorr.setFecha_registro(trabajadorr.getFecha_registro());
				_trabajadorr.setStatus(trabajadorr.getStatus());
	    	 return new ResponseEntity<>( repTrabajadores.save(_trabajadorr), HttpStatus.OK);
			 }else{
		
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
		 }
	 
	 
	 
}
