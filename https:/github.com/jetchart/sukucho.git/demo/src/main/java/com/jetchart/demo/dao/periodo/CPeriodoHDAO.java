package com.jetchart.demo.dao.periodo;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.jetchart.demo.model.CEstadoPeriodo;
import com.jetchart.demo.model.CPeriodo;
import com.jetchart.demo.util.CHDAOService;
import com.jetchart.demo.util.CPersistenceUtil;

public class CPeriodoHDAO extends CHDAOService{

	
	public static CPeriodo getPeriodoByMesAndAnio(Integer mes, Integer anio) throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		Query query = entityManager.createQuery("SELECT P FROM CPeriodo P WHERE P.mes = :mes AND P.anio = :anio");
		query.setParameter("mes", mes);
		query.setParameter("anio", anio);
		try{
			return (CPeriodo) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public static CPeriodo getPeriodoVigente() throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		Query query = entityManager.createQuery("SELECT P FROM CPeriodo P WHERE P.estadoPeriodo.id = :estadoPeriodo");
		query.setParameter("estadoPeriodo", CEstadoPeriodo.ID_VIGENTE);
		try{
			return (CPeriodo) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public static CPeriodo getMaximoPeriodo() throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		Query query = entityManager.createQuery("SELECT P FROM CPeriodo P WHERE P.id = (SELECT MAX(P2.id) FROM CPeriodo P2)");
		return (CPeriodo) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public static Collection<CPeriodo> getPeriodosPendienteAviso() throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		Query query = entityManager.createQuery("SELECT P FROM CPeriodo P WHERE P.estadoPeriodo.id = :estadoPeriodo");
		query.setParameter("estadoPeriodo", CEstadoPeriodo.ID_PENDIENTE_AVISO);
		try{
			return (Collection<CPeriodo>) query.getResultList();
		}catch(NoResultException e){
			return null;
		}
	}
	
}
