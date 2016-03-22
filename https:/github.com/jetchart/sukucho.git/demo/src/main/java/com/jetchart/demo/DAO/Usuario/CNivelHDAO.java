package com.jetchart.demo.DAO.Usuario;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import model.CMenu;
import model.CNivel;
import model.CUsuario;

import com.jetchart.demo.Util.CHDAOService;
import com.jetchart.demo.Util.CPersistenceUtil;

public class CNivelHDAO extends CHDAOService{

	@SuppressWarnings("unchecked")
	public static Collection<CNivel> getAllNivel() throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try {
			Query query = entityManager.createQuery("SELECT N FROM CNivel N");
			return (Collection<CNivel>) query.getResultList();
		} catch (PersistenceException ex) {
			return null;
		}
	}
}
