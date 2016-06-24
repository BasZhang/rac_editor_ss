package com.ourpalm.editor.dao;

import java.lang.reflect.Field;
import java.util.List;

import org.hibernate.NonUniqueResultException;
import org.hibernate.criterion.Criterion;

import com.ourpalm.editor.TableEntity;

/**
 * 对于{@code TableEntity}实体的DAO扩展。
 * 
 * @author zhangbo
 * 
 */
public interface TablesDao extends AbstractDao {

	/**
	 * 查找符合条件的所有记录。
	 * 
	 * @param <T>
	 * @param clazz
	 *            实体类型
	 * @param criterion
	 *            条件
	 * @return 符合条件的所有记录。
	 */
	public <T extends TableEntity> List<T> getAll(Class<T> clazz, Criterion... criterions);

	/**
	 * 查找符合条件的唯一记录。
	 * 
	 * @param <T>
	 * @param clazz
	 *            实体类型
	 * @param criterions
	 *            条件
	 * @return 符合条件的唯一记录。
	 * @throws NonUniqueResultException 当查询结果不唯一。
	 */
	public <T extends TableEntity> T getUniq(Class<T> clazz, Criterion... criterions) throws NonUniqueResultException;

	/**
	 * 查找符合条件的第一条记录。
	 * 
	 * @param <T>
	 * @param clazz
	 *            实体类型
	 * @param criterions
	 *            条件
	 * @return 符合条件的第一条记录。
	 */
	public <T extends TableEntity> T getFirst(Class<T> clazz, Criterion... criterions);

	/**
	 * 计数符合条件的数据条数。
	 * 
	 * @param <T>
	 * @param clazz
	 *            实体类型
	 * @param criterion
	 *            条件
	 * @return 符合条件的数据条数。
	 */
	public <T extends TableEntity> int count(Class<T> clazz, Criterion... criterions);

	/**
	 * 计数某个域的所有记录中，最多可以被英文逗号分为几列。
	 * 
	 * @param <T>
	 * @param clazz
	 *            实体类型
	 * @param field
	 *            域
	 * @return 某个域的所有记录中，最多可以被英文逗号分为几列。
	 */
	public <T extends TableEntity> long count(Class<T> clazz, Field field);
}
