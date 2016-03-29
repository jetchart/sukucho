package com.jetchart.demo.service.periodo;

import java.util.Collection;

import javax.persistence.EntityManager;

import org.joda.time.DateTime;

import com.jetchart.demo.model.CNivel;
import com.jetchart.demo.model.CPeriodo;
import com.jetchart.demo.model.CUsuario;
import com.jetchart.demo.model.CUsuarioPeriodo;
import com.jetchart.demo.business.usuario.CUsuarioBusiness;
import com.jetchart.demo.business.usuario.CUsuarioPeriodoBusiness;
import com.jetchart.demo.business.gasto.CGastoBusiness;
import com.jetchart.demo.business.nivel.CNivelBusiness;
import com.jetchart.demo.business.periodo.CPeriodoBusiness;
import com.jetchart.demo.util.CPersistenceUtil;

public class CPeriodoService {

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
	
	/* Inserta el periodo proximo, copiando los usuarios que participaron
	 * del periodo anterior */
	public static void insertarProximoPeriodo() throws Exception{
		CPeriodo maximoPeriodo = new CPeriodoBusiness().getMaximoPeriodo();
		Collection<CUsuario> colUsuarios = new CUsuarioBusiness().findPersonasByPeriodo(maximoPeriodo);
		CPeriodo periodo = new CPeriodoBusiness().armarProximoPeriodo();
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		entityManager.getTransaction().begin();
		new CPeriodoBusiness().insert(periodo);
		for (CUsuario usuario : colUsuarios){
			CUsuarioPeriodo usuarioPeriodo = new CUsuarioPeriodo();
			usuarioPeriodo.setPeriodo(periodo);
			usuarioPeriodo.setUsuario(usuario);
			new CUsuarioPeriodoBusiness().insert(usuarioPeriodo);
		}
		entityManager.getTransaction().commit();
	}
}
