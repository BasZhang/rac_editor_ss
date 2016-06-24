package com.ourpalm.editor.dao.impl;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.NonUniqueResultException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;
import com.ourpalm.editor.TableEntity;
import com.ourpalm.editor.dao.TablesDao;

/**
 * 
 * @author zhangbo
 *
 */
@Repository
public class TablesDaoImpl extends AbstractDaoImpl implements TablesDao {

	@Override
	public <T extends TableEntity> List<T> getAll(Class<T> clazz, Criterion... criterions) {
		Criteria criteria = getCurrentSession().createCriteria(clazz);
		if (criterions != null) {
			for (Criterion c : criterions) {
				criteria.add(c);
			}
		}
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) criteria.list();

		return list;
	}

	@Override
	public <T extends TableEntity> T getFirst(Class<T> clazz, Criterion... criterions) {
		List<T> all = getAll(clazz, criterions);
		if (all.size() > 0)
			return all.get(0);
		return null;
	}

	@Override
	public <T extends TableEntity> T getUniq(Class<T> clazz, Criterion... criterions) throws NonUniqueResultException {
		Criteria criteria = getCurrentSession().createCriteria(clazz);
		if (criterions != null) {
			for (Criterion c : criterions) {
				criteria.add(c);
			}
		}
		@SuppressWarnings("unchecked")
		T e = (T) criteria.uniqueResult();
		return e;
	}

	@Override
	public <T extends TableEntity> int count(Class<T> clazz, Criterion... criterions) {
		Criteria criteria = getCurrentSession().createCriteria(clazz);
		if (criterions != null) {
			for (Criterion c : criterions) {
				criteria.add(c);
			}
		}
		criteria.setProjection(Projections.rowCount());
		Long i = (Long) criteria.uniqueResult();

		return i.intValue();
	}

	@Override
	public <T extends TableEntity> long count(Class<T> clazz, Field field) {
		String sqlField = field.isAnnotationPresent(Column.class) ? field.getAnnotation(Column.class).name() : "";
		if (Strings.isNullOrEmpty(sqlField)) {
			sqlField = field.getName();
		}
		String sqlTable = clazz.getAnnotation(Table.class).name();


		String sql = "SELECT MAX(LENGTH(" + sqlField + ")-LENGTH(REPLACE(" + sqlField + ",',',''))+1) FROM " + sqlTable;
		Query q = em.createNativeQuery(sql);
		BigInteger singleResult = (BigInteger) q.getSingleResult();
		return singleResult.longValue();
	}

}
