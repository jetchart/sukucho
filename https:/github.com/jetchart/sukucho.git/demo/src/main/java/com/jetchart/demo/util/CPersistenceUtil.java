package com.jetchart.demo.util;

import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.hibernate.ejb.HibernateEntityManager;
import org.hibernate.engine.spi.SessionImplementor;

public class CPersistenceUtil {
	
	private static final ThreadLocal<EntityManager> threadEntityManager = new ThreadLocal<EntityManager>();
	private static final ThreadLocal<Connection> threadConnection = new ThreadLocal<Connection>();
	private static EntityManagerFactory entityManagerFactory = null;
	
	private static EntityManagerFactory getEntityManagerFactory() {
		
		try {
			if (entityManagerFactory == null)
				entityManagerFactory = Persistence
						.createEntityManagerFactory("persistence");
		} catch (Exception ex) {
			Logger.getRootLogger().error(ex);
		}
		return entityManagerFactory;
	}
	
	
		
	/**
	 * El método getEntityManager() debe retornar una instancia válida de un
	 * único EntityManager por HttpRequest. En caso de ya haberse instanciado un
	 * EntityManager durante el presente HttpRequest se retornará el mismo, caso
	 * contrario se instanciará y retornará un nuevo EntityManager.
	 * 
	 * @return EntityManager
	 * 
	 * @throws CException
	 * 
	 * */
	public static EntityManager getEntityManager() throws Exception {
		EntityManager entityManager = null;
		try {
			entityManager = threadEntityManager.get();
			if (entityManager == null) {
				entityManager = getEntityManagerFactory().createEntityManager();
				threadEntityManager.set(entityManager);
			}
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		//TODO REVISAR SI ESTO ARREGLA EL PROBLEMA DE LA CACHE Y SI AFECTA MUCHO A LA PERFORMANCE
		entityManager.clear();
		return entityManager;
	}

	/**
	 * El método closeEntityManager() debe cerrar, en caso de encontrarse
	 * instanciado y abierto, el EntityManager del actual HttpRequest.
	 * 
	 * @throws CException
	 * 
	 * */
	public static void closeEntityManager() throws Exception {
		EntityManager entityManager = null;
		try {
			entityManager = threadEntityManager.get();
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
			closeConnection();
		} catch (Exception ex) {
			throw new Exception(ex);
		} finally {
			threadEntityManager.set(null);
		}
	}
	
	public static Connection getConnection() throws Exception {
		Connection connection = null;
		try {
			connection = threadConnection.get();
			if (connection == null) {
				EntityManager entityManager = getEntityManager();
				HibernateEntityManager hem = (HibernateEntityManager) entityManager;
				SessionImplementor sim = (SessionImplementor) hem.getSession();
				connection = sim.connection();
				threadConnection.set(connection);
			}
		} catch (Exception ex) {
			throw new Exception(ex);
		}

		return connection;
	}
	
	private static void closeConnection() throws Exception {
		Connection connection = null;
		try {
			connection = threadConnection.get();
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (Exception ex) {
			throw new Exception(ex);
		} finally {
			threadConnection.set(null);
		}
	}
	
	public static Boolean existsConnection() throws Exception {
		Boolean existsConnection = Boolean.FALSE;
		EntityManager entityManager = null;
		try {
			entityManager = threadEntityManager.get();
			if (entityManager != null) {
				existsConnection = Boolean.TRUE;
			}
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		return existsConnection;
	}

}
