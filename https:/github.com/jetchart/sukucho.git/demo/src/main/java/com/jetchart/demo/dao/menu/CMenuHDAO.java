package com.jetchart.demo.dao.menu;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.jetchart.demo.model.CMenu;
import com.jetchart.demo.model.CUsuario;

import com.jetchart.demo.util.CHDAOService;
import com.jetchart.demo.util.CPersistenceUtil;

public class CMenuHDAO extends CHDAOService{

	@SuppressWarnings("unchecked")
	public static Collection<CMenu> getMenuByUsuario(CUsuario usuario) throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try {
//			Query query = entityManager.createQuery("SELECT M FROM CMenu M");
			Query query = entityManager.createQuery("SELECT M FROM CMenu M, CUsuario U WHERE M.nivel.id >= U.nivel.id AND U.id = :usuarioId ORDER BY M.id ASC");
			query.setParameter("usuarioId", usuario.getId());
			return (Collection<CMenu>) query.getResultList();
		} catch (PersistenceException ex) {
			return null;
		}
	}
}
