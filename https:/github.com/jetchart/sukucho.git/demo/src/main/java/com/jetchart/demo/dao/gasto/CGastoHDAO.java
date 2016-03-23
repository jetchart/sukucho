package com.jetchart.demo.dao.gasto;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jetchart.demo.model.CGasto;
import com.jetchart.demo.model.CPeriodo;
import com.jetchart.demo.model.CUsuario;
import com.jetchart.demo.util.CHDAOService;
import com.jetchart.demo.util.CPersistenceUtil;

public class CGastoHDAO extends CHDAOService{
	
	private static final Logger logger = LoggerFactory.getLogger(CGastoHDAO.class);

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
	
	public static Collection<CGasto> findByPeriodo(CPeriodo periodo) throws Exception{
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
			
			Query query = entityManager.createQuery("SELECT c FROM CGasto c WHERE c.fecha >= :fechaDesde AND c.fecha < :fechaHasta ");
			query.setParameter("fechaDesde", fechaDesde);
			query.setParameter("fechaHasta", fechaHasta);
			return (Collection<CGasto>) query.getResultList();
		} catch (PersistenceException ex) {
			return null;
		}
	}
}
