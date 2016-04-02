package com.jetchart.demo.service.usuario;

import java.util.Collection;

import javax.persistence.EntityManager;

import com.jetchart.demo.model.CMenu;
import com.jetchart.demo.model.CPeriodo;
import com.jetchart.demo.model.CUsuario;
import com.jetchart.demo.business.usuario.CUsuarioBusiness;
import com.jetchart.demo.util.CPersistenceUtil;

public class CUsuarioService {

	public static void insert(Object object) throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try{
			entityManager.getTransaction().begin();
			CUsuarioBusiness usuarioBusiness = new CUsuarioBusiness();
			usuarioBusiness.insert(object);
			entityManager.getTransaction().commit();
			entityManager.clear();
		}catch(Exception e){
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception();
		}
	}
	
	public static void update(Object object) throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try{
			entityManager.getTransaction().begin();
			CUsuarioBusiness usuarioBusiness = new CUsuarioBusiness();
			usuarioBusiness.update(object);
			entityManager.getTransaction().commit();
			entityManager.clear();
		}catch(Exception e){
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception();
		}
	}
	
	public static void delete(Object object) throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try{
			entityManager.getTransaction().begin();
			CUsuarioBusiness usuarioBusiness = new CUsuarioBusiness();
			usuarioBusiness.delete(object);
			entityManager.getTransaction().commit();
			entityManager.clear();
		}catch(Exception e){
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception();
		}
	}
	
	public static CUsuario getUsuarioByEmailAndContrasenia(String email, String contrasenia) throws Exception{
		return new CUsuarioBusiness().getUsuarioByEmailAndContrasenia(email, contrasenia);
	}
	
	public static Collection<CMenu> getMenuByUsuario(CUsuario usuario) throws Exception{
		return new CUsuarioBusiness().getMenuByUsuario(usuario);
	}
	
	public static Boolean existsPersonaByEmail(CUsuario usuario) throws Exception{
		return new CUsuarioBusiness().existsPersonaByEmail(usuario);
	}
	
	public static Boolean isUsuarioParticipantePeriodo(CUsuario usuario, CPeriodo periodo) throws Exception{
		if (usuario != null && periodo != null){
			Collection<CUsuario> usuariosPeriodo = new CUsuarioBusiness().findUsuariosByPeriodo(periodo);
			for (CUsuario u : usuariosPeriodo){
				if (u.getId().equals(usuario.getId())){
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE; 
	}
	
}
