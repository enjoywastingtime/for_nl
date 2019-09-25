package com.base.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.base.dao.BaseDao;
import com.base.entity.BaseEntity;

public class BaseServiceImpl<T extends BaseEntity, ID extends Serializable> implements BaseService<T, ID> {

	private BaseDao<T, ID> baseDao;

	public void setBaseDao(BaseDao<T, ID> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public T find(ID id) {
		return baseDao.findOne(id);
	}

	@Override
	public List<T> findAll() {
		return baseDao.findAll();
	}

	@Override
	public List<T> findAll(Sort sort) {
		return baseDao.findAll(sort);
	}

	@Transactional
	@Override
	public void save(T entity) {
		baseDao.save(entity);
	}

	@Transactional
	@Override
	public void batchSave(List<T> objs) {
		baseDao.save(objs);
	}

	@Transactional
	@Override
	public void delete(T entity) {
		baseDao.delete(entity);
	}

	@Transactional
	@Override
	public void batchDelete(List<T> objs) {
		baseDao.delete(objs);
	}

	@Transactional
	@Override
	public void delete(ID id) {
		baseDao.delete(id);
	}

	@Transactional
	@Override
	public void deleteByIds(String tableName, String ids) {
		if (StringUtils.isEmpty(ids)) {
			return;
		}
		// 考虑已经打了单引号和没打单引号的情况
		if (!ids.contains("'")) {
			ids = "'" + ids.replaceAll(",", "','") + "'";
		}
		baseDao.executeUpdateBySQL("delete from " + tableName + " where id in (" + ids + ")");
	}

}