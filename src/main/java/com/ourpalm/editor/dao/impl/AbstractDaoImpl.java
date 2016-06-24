package com.ourpalm.editor.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.data.jpa.domain.Specification;

import com.ourpalm.editor.dao.AbstractDao;
import com.ourpalm.editor.util.BaseQEntity;

public class AbstractDaoImpl implements AbstractDao {

	@PersistenceContext
	protected EntityManager em;

	protected Session getCurrentSession() {
		return em.unwrap(org.hibernate.Session.class);
	}

	@Override
	public <T> void save(T t) {
		em.persist(t);
	}

	@Override
	public <T> void update(T t) {
		em.merge(t);
	}

	@Override
	public <T> void delete(T t) {
		em.remove(t);
	}

	@Override
	public <T, I extends Serializable> T get(Class<T> clz, I id) {
		return em.getReference(clz, id);
	}

	@Override
	public <T> void delete(Class<T> entityClass, Object entityid) {
		delete(entityClass, new Object[] { entityid });
	}

	@Override
	public <T> void delete(Class<T> entityClass, Object[] entityids) {
		for (Object id : entityids) {
			em.remove(em.getReference(entityClass, id));
		}
	}

	@Override
	public <T> long getCount(Class<T> entityClass, Specification<T> specification) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);

		Root<T> root = query.from(entityClass);
		if (specification != null) {
			Predicate predicate = specification.toPredicate(root, query, cb);
			query.where(predicate);
		}
		query.select(cb.countDistinct(root));

		return (Long) em.createQuery(query).getSingleResult();
	}

	@Override
	public <T> List<T> findAll(Class<T> clazz, Specification<T> specification) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(clazz);

		Root<T> root = query.from(clazz);
		query.select(root);
		if (specification != null) {
			Predicate predicate = specification.toPredicate(root, query, cb);
			query.where(predicate);
		}
		List<T> list = em.createQuery(query).getResultList();

		return list;
	}

	@Override
	public <T> BaseQEntity<T> getPage(Class<T> t, BaseQEntity<T> b, Specification<T> specification) {

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<T> root = query.from(t);
		query.select(cb.countDistinct(root));

		if (specification != null) {
			Predicate predicate = specification.toPredicate(root, query, cb);
			query.where(predicate);
		}
		Long total = em.createQuery(query).getSingleResult();
		b.setTotal(total);
		// //////////////////////////////////////////////////////////////////////////////////
		CriteriaQuery<T> createQuery = cb.createQuery(t);
		Root<T> rot = createQuery.from(t);
		createQuery.select(rot);
		if (specification != null) {
			Predicate predicate = specification.toPredicate(root, query, cb);
			createQuery.where(predicate);
		}
		Query tq = em.createQuery(createQuery);
		tq.setFirstResult((b.getCurrentPage() - 1) * b.getPerPageRows());
		tq.setMaxResults(b.getPerPageRows());
		@SuppressWarnings("unchecked")
		List<T> resultList = (List<T>) tq.getResultList();
		b.setRows(resultList);

		return b;
	}

}
