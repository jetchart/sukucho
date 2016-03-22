package com.jetchart.demo.Service.Usuario;

import java.util.Collection;

import javax.persistence.EntityManager;

import model.CMenu;
import model.CNivel;
import model.CUsuario;

import com.jetchart.demo.Business.Usuario.CNivelBusiness;
import com.jetchart.demo.Business.Usuario.CUsuarioBusiness;
import com.jetchart.demo.Util.CPersistenceUtil;

public class CNivelService {

	public static void insert(Object object) throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		entityManager.getTransaction().begin();
		CUsuarioBusiness usuarioBusiness = new CUsuarioBusiness();
		usuarioBusiness.insert(object);
		entityManager.getTransaction().commit();
	}
	
	public static void update(Object object) throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		entityManager.getTransaction().begin();
		CUsuarioBusiness usuarioBusiness = new CUsuarioBusiness();
		usuarioBusiness.update(object);
		entityManager.getTransaction().commit();
	}
	
	public static void delete(Object object) throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		entityManager.getTransaction().begin();
		CUsuarioBusiness usuarioBusiness = new CUsuarioBusiness();
		usuarioBusiness.delete(object);
		entityManager.getTransaction().commit();
	}
	

	public static Collection<CNivel> getAllNivel() throws Exception{
		return new CNivelBusiness().getAllNivel();
	}
}
