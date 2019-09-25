package com.base.service;

import java.io.Serializable;
import java.util.List;

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

	public void deleteByIds(String tableName,String ids);

}
