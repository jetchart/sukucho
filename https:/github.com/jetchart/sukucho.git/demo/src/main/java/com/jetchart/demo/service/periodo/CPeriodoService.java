package com.jetchart.demo.service.periodo;

import java.util.Collection;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jetchart.demo.business.email.CEmailBusiness;
import com.jetchart.demo.business.periodo.CPeriodoBusiness;
import com.jetchart.demo.business.usuario.CUsuarioBusiness;
import com.jetchart.demo.business.usuario.CUsuarioPeriodoBusiness;
import com.jetchart.demo.model.CEstadoPeriodo;
import com.jetchart.demo.model.CPeriodo;
import com.jetchart.demo.model.CUsuario;
import com.jetchart.demo.model.CUsuarioPeriodo;
import com.jetchart.demo.util.CPersistenceUtil;
import com.jetchart.demo.util.CUtil;

public class CPeriodoService {
	
	private static final Logger logger = LoggerFactory.getLogger(CPeriodoService.class);
	
	public static void insert(Object object) throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try{
			entityManager.getTransaction().begin();
			CUsuarioBusiness usuarioBusiness = new CUsuarioBusiness();
			usuarioBusiness.insert(object);
			entityManager.getTransaction().commit();
			entityManager.clear();
		}catch(Exception e){
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception();
		}
	}
	
	public static void update(Object object) throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try{
			entityManager.getTransaction().begin();
			CUsuarioBusiness usuarioBusiness = new CUsuarioBusiness();
			usuarioBusiness.update(object);
			entityManager.getTransaction().commit();
			entityManager.clear();
		}catch(Exception e){
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception();
		}
	}
	
	public static void delete(Object object) throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try{
			entityManager.getTransaction().begin();
			CUsuarioBusiness usuarioBusiness = new CUsuarioBusiness();
			usuarioBusiness.delete(object);
			entityManager.getTransaction().commit();
			entityManager.clear();
		}catch(Exception e){
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception();
		}
	}
	
	/* Inserta el periodo proximo, copiando los usuarios que participaron
	 * del periodo anterior */
	public static void insertarProximoPeriodo() throws Exception{
		CPeriodo periodoVigente = new CPeriodoBusiness().getPeriodoVigente();
		Collection<CUsuario> colUsuarios = new CUsuarioBusiness().findUsuariosByPeriodo(periodoVigente);
		CPeriodo periodo = new CPeriodoBusiness().armarProximoPeriodo();
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
			try{
			entityManager.getTransaction().begin();
			new CPeriodoBusiness().insert(periodo);
			for (CUsuario usuario : colUsuarios){
				CUsuarioPeriodo usuarioPeriodo = new CUsuarioPeriodo();
				usuarioPeriodo.setPeriodo(periodo);
				usuarioPeriodo.setUsuario(usuario);
				new CUsuarioPeriodoBusiness().insert(usuarioPeriodo);
			}
			entityManager.getTransaction().commit();
			entityManager.clear();
		}catch(Exception e){
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception();
		}
	}
	
	public static void cerrarPeriodoVigente() throws Exception{
		/* Se coloca al periodo vigente el estado PENDIENTE_AVISO */
		CPeriodo periodoVigente = new CPeriodoBusiness().getPeriodoVigente();
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try{
			entityManager.getTransaction().begin();
			CEstadoPeriodo estadoPeriodo = new CEstadoPeriodo();
			estadoPeriodo.setId(CEstadoPeriodo.ID_PENDIENTE_AVISO);
			periodoVigente.setEstadoPeriodo(estadoPeriodo);
			new CPeriodoBusiness().update(periodoVigente);
			entityManager.getTransaction().commit();
			entityManager.clear();
		}catch(Exception e){
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception();
		}
	}
	
	/**
	 * Este método envía mails a los {@link CPeriodo} con estado pendiente de aviso y se los coloca en estado cerrado (ID_CERRADO).
	 * @throws Exception
	 */
	public static void enviarMailPeriodosPendientesAviso() throws Exception{
		Collection<CPeriodo> periodosPendientesAviso = new CPeriodoBusiness().getPeriodosPendienteAviso();
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try{
			entityManager.getTransaction().begin();
			for (CPeriodo periodo : periodosPendientesAviso){
				CEstadoPeriodo estadoPeriodo = new CEstadoPeriodo();
				estadoPeriodo.setId(CEstadoPeriodo.ID_CERRADO);
				periodo.setEstadoPeriodo(estadoPeriodo);
				new CPeriodoBusiness().update(periodo);
				CUtil.enviarMail(new CEmailBusiness().getEmailCierrePeriodo(periodo,null));
			}
			entityManager.getTransaction().commit();
			entityManager.clear();
		}catch(Exception e){
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception();
		}
	}
	
	/**
	 * Inserta el nuevo {@link CPeriodo} vigente (con estado ID_VIGENTE) copiando los {@link CUsuario} que participaron del periodo anterior.
	 * Además, coloca al periodo anterior en estado pendiente aviso (ID_PENDIENTE_AVISO), para que el metodo "enviarMailPeriodosPendientesAviso",
	 * lo procese, envíe los mails y lo cierre.
	 * <br>
	 * Si ya existe un periodo vigente, se omite este metodo (este caso podría pasar en caso que se corra este método mas de 1 vez en el mes).
	 * <br>
	 * A través de script's se pueden crear periodos futuros (con el objetivo de dejarlo preparado para cuando sea vigente), para
	 * este caso, tambien tienen que agregarse por script los usuarios que conformarán ese periodo ({@link CUsuarioPeriodo}). En este caso,
	 * cuando se ejecute este método, en vez de crear un periodo vigente, obtendrá el futuro y lo hará le colocará estado vigente (ID_VIGENTE).
	 * @throws Exception
	 */
	public static void insertaNuevoPeriodoVigente() throws Exception{
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try{
			entityManager.getTransaction().begin();
			/* periodoVigente --> Es el periodo que tiene ESTADO_PERIODO = VIGENTE (1) */
			CPeriodo periodoVigente = new CPeriodoBusiness().getPeriodoVigente();
			/* periodoMesAnio --> Es el periodo que tiene el MES y ANIO actual, o sea sería el periodo vigente real */
			CPeriodo periodoMesAnio = new CPeriodoBusiness().armarPeriodoVigente();
			/* Por lo tanto, si periodoVigente = periodoMesAnio significa que ya existe un periodo vigente real y no debe hacerse nada.
			 * ACLARACION: este caso podría pasar en caso que se corra este metodo mas de 1 vez en el mes. */
			if (periodoVigente.getMes() != periodoMesAnio.getMes() && periodoVigente.getAnio() != periodoMesAnio.getAnio()){
				/* Caso en el que se agrega un Periodo FUTURO por afuera (script) y llega el momento en 
				 * el que se hace vigente (tambien deben agregarse los USUARIO_PERIODO.
				 * ATENCION: Los usuarios que participan del periodo FUTURO deben agregarse tambien a MANO, este 
				 * proceso no lo contempla para los futuros. */
				CPeriodo periodoNuevo = new CPeriodoBusiness().getPeriodoByMesAndAnio(periodoMesAnio.getMes(), periodoVigente.getAnio());
				if (periodoNuevo != null && CEstadoPeriodo.ID_FUTURO.equals(periodoNuevo.getEstadoPeriodo().getId())){
					CEstadoPeriodo estadoPeriodo = new CEstadoPeriodo();
					estadoPeriodo.setId(CEstadoPeriodo.ID_VIGENTE);
					periodoNuevo.setEstadoPeriodo(estadoPeriodo);
					new CPeriodoBusiness().update(periodoNuevo);
					logger.info("Periodo FUTURO pasó a ser VIGENTE");
				}else{
					periodoNuevo = new CPeriodoBusiness().armarPeriodoVigente();
					/* Inserto nuevo periodo vigente */
					new CPeriodoBusiness().insert(periodoNuevo);
					logger.info("Periodo VIGENTE creado");
					/* Copio al nuevo periodo las personas que participaron del periodo anterior */
					Collection<CUsuario> colUsuarios = new CUsuarioBusiness().findUsuariosByPeriodo(periodoVigente);
					for (CUsuario usuario : colUsuarios){
						CUsuarioPeriodo usuarioPeriodo = new CUsuarioPeriodo();
						usuarioPeriodo.setPeriodo(periodoNuevo);
						usuarioPeriodo.setUsuario(usuario);
						new CUsuarioPeriodoBusiness().insert(usuarioPeriodo);
					}
					logger.info("Usuarios agregados al nuevo periodo VIGENTE");
				}
				/* Cambio estado del periodo ya que no es mas el vigente */
				CEstadoPeriodo estadoPeriodo = new CEstadoPeriodo();
				estadoPeriodo.setId(CEstadoPeriodo.ID_PENDIENTE_AVISO);
				periodoVigente.setEstadoPeriodo(estadoPeriodo);
				new CPeriodoBusiness().update(periodoVigente);
				logger.info("Estado del ex periodo VIGENTE cambiado a PENDIENTE_AVISO");
				entityManager.getTransaction().commit();
				entityManager.clear();
			}else {
				logger.info("No se realizaron cambios debido a que ya existe un periodo VIGENTE real");
			}
		}catch(Exception e){
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception();
		}
	}
	
}
