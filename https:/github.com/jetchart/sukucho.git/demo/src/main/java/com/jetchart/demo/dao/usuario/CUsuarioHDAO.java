package com.jetchart.demo.dao.usuario;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.jetchart.demo.model.CUsuario;
import com.jetchart.demo.util.CHDAOService;
import com.jetchart.demo.util.CPersistenceUtil;

public class CUsuarioHDAO extends CHDAOService{

	public static CUsuario getUsuarioByEmailAndContrasenia(String email, String contrasenia) throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try {
			Query query = entityManager.createQuery("SELECT c FROM CUsuario c WHERE c.email = :email AND c.contrasenia = :contrasenia");
			query.setParameter("email", email);
			query.setParameter("contrasenia", contrasenia);
			return (CUsuario) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public static Boolean existsPersonaByEmail(CUsuario usuario) throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try {
		Query query = entityManager.createQuery("SELECT c FROM CUsuario c WHERE c.email = :email AND (c.id <> :usuarioId OR :usuarioId IS NULL)");
		query.setParameter("email", usuario.getEmail());
		query.setParameter("usuarioId", usuario.getId());
		return (query.getResultList().size() > 0);
		} catch (NoResultException ex) {
			return null;
		}
	}
}
