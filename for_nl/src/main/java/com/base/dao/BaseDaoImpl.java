package com.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.base.entity.BaseEntity;

public class BaseDaoImpl<T extends BaseEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseDao<T, ID> {

	private final EntityManager entityManager;

	public BaseDaoImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager = em;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * 查询 返回集合
	 * 
	 * @param sql
	 * @param objects
	 *            占位符参数数组
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findListBySql(String sql, Object[] objects) {
		Query q = this.entityManager.createNativeQuery(sql);
		if (objects != null && objects.length > 0) {
			for (int i = 0; i < objects.length; ++i) {
				q.setParameter(i + 1, objects[i]);
			}
		}

		((SQLQuery) q.unwrap(SQLQuery.class)).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return q.getResultList();
	}

	/**
	 * 查询单个结果集
	 * 
	 * @param sql
	 * @param objects
	 *            占位符参数数组
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> findOneBySql(String sql, Object[] objects) {
		Query q = this.entityManager.createNativeQuery(sql);
		if (objects != null && objects.length > 0) {
			for (int i = 0; i < objects.length; ++i) {
				q.setParameter(i + 1, objects[i]);
			}
		}

		((SQLQuery) q.unwrap(SQLQuery.class)).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return (Map<String, Object>) q.getSingleResult();
	}

	/**
	 * 查询分页集合
	 * 
	 * @param pageable
	 *            分页对象 Pageable pageable = new PageRequest(0,10);
	 * @param sql
	 * @param objects
	 *            占位符参数数组
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<Map<String, Object>> findPageBySql(Pageable pageable, String sql, Object[] objects) {

		Query q = this.entityManager.createNativeQuery(sql);
		Query sizeQuery = this.entityManager.createNativeQuery("select count(*) from (" + sql + ")");
		if (objects != null && objects.length > 0) {
			for (int i = 0; i < objects.length; ++i) {
				q.setParameter(i + 1, objects[i]);
				sizeQuery.setParameter(i + 1, objects[i]);
			}
		}

		long size = 0L;
		if (pageable != null) {
			size = Long.valueOf(sizeQuery.getResultList().get(0).toString());
			q.setFirstResult(pageable.getOffset());
			q.setMaxResults(pageable.getPageSize());
		}

		((SQLQuery) q.unwrap(SQLQuery.class)).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return pageable == null ? new PageImpl<Map<String, Object>>(q.getResultList()) : new PageImpl<Map<String, Object>>(q.getResultList(), pageable, size);
	}

	/**
	 * 查询 返回集合
	 * 
	 * @param sql
	 * @param objects
	 *            占位符参数数组
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findListEntityBySql(String sql, Object[] objects, Class<T> clazz) {
		Query q = this.entityManager.createNativeQuery(sql, clazz);
		if (objects != null && objects.length > 0) {
			for (int i = 0; i < objects.length; ++i) {
				q.setParameter(i + 1, objects[i]);
			}
		}
		return q.getResultList();
	}

	/**
	 * 查询单个结果集
	 * 
	 * @param sql
	 * @param objects
	 *            占位符参数数组
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public T findOneEntityBySql(String sql, Object[] objects, Class<T> clazz) {
		Query q = this.entityManager.createNativeQuery(sql, clazz);
		if (objects != null && objects.length > 0) {
			for (int i = 0; i < objects.length; ++i) {
				q.setParameter(i + 1, objects[i]);
			}
		}
		return (T) q.getSingleResult();
	}

	/**
	 * 查询分页集合
	 * 
	 * @param pageable
	 *            分页对象 Pageable pageable = new PageRequest(0,10);
	 * @param sql
	 * @param objects
	 *            占位符参数数组
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findPageEntityBySql(Pageable pageable, String sql, Object[] objects, Class<T> clazz) {

		Query q = this.entityManager.createNativeQuery(sql, clazz);
		Query sizeQuery = this.entityManager.createNativeQuery("select count(*) from (" + sql + ")");
		if (objects != null && objects.length > 0) {
			for (int i = 0; i < objects.length; ++i) {
				q.setParameter(i + 1, objects[i]);
				sizeQuery.setParameter(i + 1, objects[i]);
			}
		}

		long size = 0L;
		if (pageable != null) {
			size = Long.valueOf(sizeQuery.getResultList().get(0).toString());
			q.setFirstResult(pageable.getOffset());
			q.setMaxResults(pageable.getPageSize());
		}
		return pageable == null ? new PageImpl<T>(q.getResultList()) : new PageImpl<T>(q.getResultList(), pageable, size);
	}

	public int getCountBySQL(String queryString) {
		int intCount = 0;
		try {
			Query q = this.getEntityManager().createNativeQuery(queryString);
			intCount = Integer.parseInt(q.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return intCount;
	}

	public int executeUpdateBySQL(String queryString) {
		int intCount = 0;
		try {
			intCount = this.getEntityManager().createNativeQuery(queryString).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return intCount;
	}

}