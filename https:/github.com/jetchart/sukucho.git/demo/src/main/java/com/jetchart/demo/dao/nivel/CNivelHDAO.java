package com.jetchart.demo.dao.nivel;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.jetchart.demo.model.CNivel;

import com.jetchart.demo.util.CHDAOService;
import com.jetchart.demo.util.CPersistenceUtil;

public class CNivelHDAO extends CHDAOService{

	@SuppressWarnings("unchecked")
	public static Collection<CNivel> getAllNivel() throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try {
			Query query = entityManager.createQuery("SELECT N FROM CNivel N WHERE id <> :nivelPublico");
			query.setParameter("nivelPublico", CNivel.ID_PUBLICO);
			return (Collection<CNivel>) query.getResultList();
		} catch (PersistenceException ex) {
			return null;
		}
	}
}
