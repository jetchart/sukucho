package com.jetchart.demo.Util;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class CHDAOService {
	public CHDAOService() {
	}

	public static void save(Object object) throws Exception {
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try {
			entityManager.persist(object);
		} catch (PersistenceException ex) {
			throw new Exception( ex);
		}
	}

	public static void insert(Object object) throws Exception {
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try {
			entityManager.persist(object);
		} catch (PersistenceException ex) {
			throw new Exception( ex);
		}
	}

	public static Object update(Object object) throws Exception {
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try {
			return entityManager.merge(object);
		} catch (PersistenceException ex) {
			throw new Exception( ex);
		}
	}

	public static void delete(Object object) throws Exception {
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		try {
			entityManager.remove(
				entityManager.contains(object) ? object : entityManager.merge(object)
			);
		} catch (PersistenceException ex) {
			throw new Exception( ex);
		}
	}

	public static Collection findAll(Object object) throws Exception {
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		List list = null;
		String clase = "";
		try {
			clase = object.getClass().getSimpleName();
			Query query = entityManager.createQuery("SELECT c FROM " + clase + " c");
			list = query.getResultList();
			return list;
		} catch (PersistenceException ex) {
			throw new Exception( ex);
		}
	}

	public static Collection findAllOrderBy(Object object, String orderBy)
			throws Exception {
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		List list = null;
		String clase = "";
		try {
			clase = object.getClass().getName();
			Query query = entityManager.createQuery("FROM " + clase
					+ " alias Order By alias." + orderBy.trim());
			list = query.getResultList();
			return list;
		} catch (PersistenceException ex) {
			throw new Exception( ex);
		}
	}

	public static Collection findAllOrderBy(Object object, String orderBy1,
			String orderBy2) throws Exception {
		EntityManager entityManager = CPersistenceUtil.getEntityManager();
		List list = null;
		String clase = "";
		try {
			clase = object.getClass().getName();
			Query query = entityManager.createQuery("FROM " + clase
					+ " alias Order By alias." + orderBy1.trim() + " , alias."
					+ orderBy2.trim());
			list = query.getResultList();
			return list;
		} catch (PersistenceException ex) {
			throw new Exception( ex);
		}
	}

	public static Object findById(Object object, String id) throws Exception {
		try {
			EntityManager entityManager = CPersistenceUtil.getEntityManager();
			object = entityManager.find(object.getClass(), id);
			return object;
		} catch (PersistenceException ex) {
			throw new Exception( ex);
		}
	}

	public static Object findById(Object object, Long id) throws Exception {
		try {
			EntityManager entityManager = CPersistenceUtil.getEntityManager();
			object = entityManager.find(object.getClass(), id);
			return object;
		} catch (PersistenceException ex) {
			throw new Exception( ex);
		}
	}

	public static Object findById(Object object, Integer id) throws Exception {
		try {
			EntityManager entityManager = CPersistenceUtil.getEntityManager();
			object = entityManager.find(object.getClass(), id);
			return object;
		} catch (PersistenceException ex) {
			throw new Exception( ex);
		}
	}

}
