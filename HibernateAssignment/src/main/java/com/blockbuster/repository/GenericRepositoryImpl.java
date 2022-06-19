package com.blockbuster.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class GenericRepositoryImpl implements GenericRepository {

	protected static EntityManagerFactory entityManagerFactory;

	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
		Runtime.getRuntime().addShutdownHook(new Thread(() -> entityManagerFactory.close()));
	}

	@Override
	public Object save(Object obj) {
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			entityManager.merge(obj);
			tx.commit();
		} finally {
			entityManager.close();
		}
		return obj;
	}

	@Override
	public void saveBatch(List<Object> obj) {
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			for (Object object : obj) {
				entityManager.merge(object);
			}
			tx.commit();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public <E> E findById(Class<E> clazz, Object pk) {
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			E e = entityManager.find(clazz, pk);
			return e;
		} finally {
			entityManager.close();
		}
	}

}
