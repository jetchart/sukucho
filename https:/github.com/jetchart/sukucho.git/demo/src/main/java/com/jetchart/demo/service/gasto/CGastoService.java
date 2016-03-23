package com.jetchart.demo.service.gasto;

import java.util.Collection;

import javax.persistence.EntityManager;

import com.jetchart.demo.model.CGasto;
import com.jetchart.demo.model.CMenu;
import com.jetchart.demo.model.CPeriodo;
import com.jetchart.demo.model.CUsuario;
import com.jetchart.demo.business.gasto.CGastoBusiness;
import com.jetchart.demo.business.usuario.CUsuarioBusiness;
import com.jetchart.demo.dao.gasto.CGastoHDAO;
import com.jetchart.demo.util.CPersistenceUtil;

public class CGastoService {

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
	
	public static CUsuario getUsuarioByEmailAndContrasenia(String email, String contrasenia) throws Exception{
		return new CUsuarioBusiness().getUsuarioByEmailAndContrasenia(email, contrasenia);
	}
	
	public static Collection<CMenu> getMenuByUsuario(CUsuario usuario) throws Exception{
		return new CUsuarioBusiness().getMenuByUsuario(usuario);
	}
	
	public static Collection<CGasto> findByPeriodo(CPeriodo periodo) throws Exception{
		return new CGastoBusiness().findByPeriodo(periodo);
	}
	
	public static Collection<Object[]> findTotalPersonasByPeriodo(CPeriodo periodo) throws Exception{
		return new CGastoBusiness().findTotalPersonasByPeriodo(periodo);
	}
}
