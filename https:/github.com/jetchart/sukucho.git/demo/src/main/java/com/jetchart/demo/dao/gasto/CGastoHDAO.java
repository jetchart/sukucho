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
			Query query = entityManager.createQuery("SELECT c FROM CGasto c WHERE c.periodo.id = :periodoId ");
			query.setParameter("periodoId", periodo.getId());
			return (Collection<CGasto>) query.getResultList();
		} catch (PersistenceException ex) {
			return null;
		}
	}
	
	public static Collection<Object[]> findTotalPersonasByPeriodo(CPeriodo periodo) throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try {
			Query query = entityManager.createNativeQuery("SELECT CONCAT(u.nombre, ' ', u.apellido), IFNULL(SUM(c.precio),0), u.email FROM USUARIO u INNER JOIN USUARIO_PERIODO UP ON (u.id = UP.usuario_id AND UP.periodo_id = :periodoId) LEFT JOIN GASTO c ON (UP.usuario_id = c.usuario_id AND c.periodo_id = UP.periodo_id) GROUP BY u.id");
			query.setParameter("periodoId", periodo.getId());
			/* DONDE: 
			 * [0] Nombre y apellido
			 * [1] Gasto 
			 * [2] Email */
			return (Collection<Object[]>) query.getResultList();
		} catch (PersistenceException ex) {
			return null;
		}
	}
}
