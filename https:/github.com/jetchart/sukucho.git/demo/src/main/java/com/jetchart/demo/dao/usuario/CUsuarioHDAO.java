package com.jetchart.demo.dao.usuario;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jetchart.demo.dao.gasto.CGastoHDAO;
import com.jetchart.demo.model.CPeriodo;
import com.jetchart.demo.model.CUsuario;
import com.jetchart.demo.util.CHDAOService;
import com.jetchart.demo.util.CPersistenceUtil;

public class CUsuarioHDAO extends CHDAOService{

	private static final Logger logger = LoggerFactory.getLogger(CGastoHDAO.class);
	
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
	
	public static Collection<CUsuario> findPersonasByPeriodo(CPeriodo periodo) throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try {
			/* Armo fechaDesde y fechaHasta*/
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.set(periodo.getAnio(), periodo.getMes()-1, 01,0,0,0);
			Timestamp fechaDesde = new Timestamp(calendar.getTimeInMillis());
			logger.info("fechaDesde: " + fechaDesde);
			calendar.set(periodo.getMes().equals(12)?periodo.getAnio()+1:periodo.getAnio(), periodo.getMes().equals(12)?0:periodo.getMes(), 01,0,0,0);
			Timestamp fechaHasta = new Timestamp(calendar.getTimeInMillis());
			logger.info("fechaHasta: " + fechaHasta);
			
			Query query = entityManager.createQuery("SELECT u FROM CUsuario u WHERE u.fechaAlta < :fechaHasta");
			query.setParameter("fechaHasta", fechaHasta);
			return (Collection<CUsuario>) query.getResultList();
		} catch (PersistenceException ex) {
			return null;
		}
	}
}
