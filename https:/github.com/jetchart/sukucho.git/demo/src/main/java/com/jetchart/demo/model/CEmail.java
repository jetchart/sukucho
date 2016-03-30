package com.jetchart.demo.model;

public class CEmail {
	private String asunto;
	private String cuerpo;
	private String[] colEmails;
	
	public CEmail(){
		
	}
	
	public String getAsunto(){
		return asunto;
	}
	
	public String getCuerpo(){
		return cuerpo;
	}
	
	public String[] getColEmails(){
		return colEmails;
	}
	
	public void setAsunto(String asunto){
		this.asunto = asunto;
	}
	
	public void setCuerpo(String cuerpo){
		this.cuerpo = cuerpo;
	}
	
	public void setColEmails(String[] colEmails){
		this.colEmails = colEmails;
	}
}
