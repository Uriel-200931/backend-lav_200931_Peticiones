package com.utxicotepec.lavado.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity   

@Table(name="venta") 

public class venta {

	  @Id   
	  @GeneratedValue(strategy = GenerationType.AUTO)   
  public Long idventa; 
  @Column(name="fecha")
  public String fecha;
  @Column(name="idcajero")
  public Long idcajero;
  @Column(name="idservicio")
  public Long idservicio;
  @Column(name="idtrabajador")
  public Long idtrabajador;
  @Column(name="idvehiculo")
  public Long idvehiculo;
  
public Long getIdventa() {
	return idventa;
}
/*public void setIdventa(Long idventa) {
	this.idventa = idventa;
}*/
public String getFecha() {
	return fecha;
}
public void setFecha(String fecha) {
	this.fecha = fecha;
}
public Long getIdcajero() {
	return idcajero;
}
public void setIdcajero(Long idcajero) {
	this.idcajero = idcajero;
}
public Long getIdservicio() {
	return idservicio;
}
public void setIdservicio(Long idservicio) {
	this.idservicio = idservicio;
}
public Long getIdtrabajador() {
	return idtrabajador;
}
public void setIdtrabajador(Long idtrabajador) {
	this.idtrabajador = idtrabajador;
}
public Long getIdvehiculo() {
	return idvehiculo;
}
public void setIdvehiculo(Long idvehiculo) {
	this.idvehiculo = idvehiculo;
}
public venta(String fecha, Long idcajero, Long idservicio, Long idtrabajador, Long idvehiculo) {
	this.fecha = fecha;
	this.idcajero = idcajero;
	this.idservicio = idservicio;
	this.idtrabajador = idtrabajador;
	this.idvehiculo = idvehiculo;
}
public venta() {
	
}
  
  
}
