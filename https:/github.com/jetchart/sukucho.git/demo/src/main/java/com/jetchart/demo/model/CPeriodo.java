package com.jetchart.demo.model;

public class CPeriodo {
	private Integer mes;
	private Integer anio;
	
	public static final String ESTADO_VIGENTE = "Vigente";
	public static final String ESTADO_CERRADO = "Cerrado";
	
	public CPeriodo(){
		
	}
	
	public CPeriodo(Integer mes, Integer anio){
		this.mes = mes;
		this.anio = anio;
	}
	
	public Integer getMes(){
		return this.mes;
	}
	
	public Integer getAnio(){
		return this.anio;
	}
	
	public void setMes(Integer mes){
		this.mes = mes;
	}
	
	public void setAnio(Integer anio){
		this.anio = anio;
	}
}
