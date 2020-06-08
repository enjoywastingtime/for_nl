package com.base.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.base.entity.BaseEntity;

public interface BaseService<T extends BaseEntity, ID extends Serializable> {

	public T find(ID id);

	public List<T> findAll();

	public List<T> findAll(Sort sort);

	public void save(T entity);

	public void batchSave(List<T> objs);

	public void delete(T entity);

	public void batchDelete(List<T> objs);

	public void delete(ID id);

	public void deleteByIds(String tableName, String ids);

	public Page<T> findPageEntityBySql(Pageable pageable, String sql, Object[] objects, Class<T> clazz);

	public List<T> findListEntityBySql(String sql, Object[] objects, Class<T> clazz);

	public Page<Map<String, Object>> findPageBySql(Pageable pageable, String sql, Object[] objects);

	public List<Map<String, Object>> findListBySql(String sql, Object[] objects);

	public Map<String, Object> findOneBySql(String sql, Object[] objects);

	public T findOneEntityBySql(String sql, Object[] objects, Class<T> clazz);

}
