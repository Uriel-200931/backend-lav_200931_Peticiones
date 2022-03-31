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

import com.utxicotepec.lavado.model.cliente;
import com.utxicotepec.lavado.repository.clienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController               
@RequestMapping("/api")             
public class clienteController {
   
	@Autowired               
	clienteRepository repClientes;            
	
	@GetMapping("/clientes")  
	
	public ResponseEntity<List<cliente>> getAllClientes (@RequestParam(required =false)String nombre){
    
		try {              
     List<cliente> clientee =new ArrayList<cliente>();     
     if(nombre==null)	 
		 repClientes.findAll().forEach(clientee::add);  
	 else
	 repClientes.findByNombre(nombre).forEach(clientee::add); 
		
     if (clientee.isEmpty()) {   
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT); 
		} 
     
     return new ResponseEntity<>(clientee, HttpStatus.OK);  
    		 
     } catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	} 
     
	}
	
	
	
	
	
	
	@GetMapping("/clientes/{idcliente}")
	public ResponseEntity<cliente> getclienteById (@PathVariable("idcliente") Long idcliente){
	Optional<cliente> clienteData =repClientes.findById(idcliente);
	if (clienteData.isPresent()) {
		return new ResponseEntity<>(clienteData.get(), HttpStatus.OK);
	}else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	}
	

	@PostMapping("/clientes")
	 public ResponseEntity<cliente> createcliente(@RequestBody cliente clientees){
		 try {
			 cliente  _clientees= repClientes 
					 .save(new cliente(  
							 
							 clientees.getNombre(),
							 clientees.getAmaterno(),  
							 clientees.getApaterno(),
							 clientees.getDireccion(),
							 clientees.getTelefono(),
							 clientees.getCorreo(),
							 clientees.getFecha_registro(),
							 clientees.getStatus()
							 ));
			 return new ResponseEntity<>( _clientees, HttpStatus.CREATED); 
		 }catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	 }
	 
	
	 @DeleteMapping("/clientes/{idcliente}")
	 public ResponseEntity<HttpStatus> deletecliente(@PathVariable("idcliente")Long idcliente){
		 try {
			 repClientes.deleteById(idcliente); /*BUSCA EN NUESTRO REPOSIRIO DE NUESTRO ID Y RETRONA UN VALOR Y POR EL TRY EL ERROR EN CASO DE QUE EXISTA SE VCISUALIZA*/
			 return new ResponseEntity<> (HttpStatus.NO_CONTENT);
		 }catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }
	 
	 
	 @PutMapping("/clientes/{idcliente}")
	 public ResponseEntity<cliente> updateCliente (@PathVariable("idcliente") Long idcliente, @RequestBody cliente clientees ){
			Optional<cliente> clienteData =repClientes.findById(idcliente);
			if (clienteData.isPresent()) {
	    	 cliente _clientees= clienteData.get();
	    	 _clientees.setNombre(clientees.getNombre());
	    	 _clientees.setApaterno(clientees.getApaterno());
	    	 _clientees.setAmaterno(clientees.getAmaterno());
	    	 _clientees.setDireccion(clientees.getDireccion());
	    	 _clientees.setCorreo(clientees.getCorreo());
	    	 _clientees.setTelefono(clientees.getTelefono());
	    	 _clientees.setFecha_registro(clientees.getFecha_registro());
	    	 _clientees.setStatus(clientees.getStatus());
	    	 
	    	 return new ResponseEntity<>( repClientes.save(_clientees), HttpStatus.OK);
			 }else{
		
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
		 }
	 
	 
	 
}
