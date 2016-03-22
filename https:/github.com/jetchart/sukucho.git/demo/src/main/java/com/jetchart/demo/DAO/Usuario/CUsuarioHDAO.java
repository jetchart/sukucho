package com.jetchart.demo.DAO.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import model.CUsuario;

import com.jetchart.demo.Util.CHDAOService;
import com.jetchart.demo.Util.CPersistenceUtil;

public class CUsuarioHDAO extends CHDAOService{

	public static CUsuario getUsuarioByEmailAndContrasenia(String email, String contrasenia) throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try {
			Query query = entityManager.createQuery("SELECT c FROM CUsuario c WHERE c.email = :email AND c.contrasenia = :contrasenia");
			query.setParameter("email", email);
			query.setParameter("contrasenia", contrasenia);
			return (CUsuario) query.getSingleResult();
		} catch (PersistenceException ex) {
			return null;
		}
	}
}
