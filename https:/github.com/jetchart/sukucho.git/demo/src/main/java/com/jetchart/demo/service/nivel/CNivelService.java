package com.jetchart.demo.service.nivel;

import java.util.Collection;

import javax.persistence.EntityManager;

import com.jetchart.demo.model.CNivel;
import com.jetchart.demo.business.usuario.CUsuarioBusiness;
import com.jetchart.demo.business.nivel.CNivelBusiness;
import com.jetchart.demo.util.CPersistenceUtil;

public class CNivelService {

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
	

	public static Collection<CNivel> getAllNivel() throws Exception{
		return new CNivelBusiness().getAllNivel();
	}
}
