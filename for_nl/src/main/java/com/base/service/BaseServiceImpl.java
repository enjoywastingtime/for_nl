package com.base.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.base.dao.BaseDao;
import com.base.entity.BaseEntity;
import com.base.util.CheckUtil;
import com.shiro.entity.SysUser;

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
		Subject subject = SecurityUtils.getSubject();
		SysUser user = (SysUser) subject.getSession().getAttribute("sysUser");
		Date now = new Date();
		if (CheckUtil.isEmpty(entity.getId())) {
			entity.setCreateBy(user);
			entity.setLastUpdateBy(user);
		} else {
			entity.setLastUpdateBy(user);
			entity.setLastUpdateDate(now);
		}
		baseDao.save(entity);
	}

	@Transactional
	@Override
	public void batchSave(List<T> objs) {
		Subject subject = SecurityUtils.getSubject();
		SysUser user = (SysUser) subject.getSession().getAttribute("sysUser");
		Date now = new Date();
		for (T entity : objs) {
			if (CheckUtil.isEmpty(entity.getId())) {
				entity.setCreateBy(user);
				entity.setLastUpdateBy(user);
			} else {
				entity.setLastUpdateBy(user);
				entity.setLastUpdateDate(now);
			}
		}
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

	public Page<T> findPageEntityBySql(Pageable pageable, String sql, Object[] objects, Class<T> clazz) {
		return baseDao.findPageEntityBySql(pageable, sql, objects, clazz);
	}

	public List<T> findListEntityBySql(String sql, Object[] objects, Class<T> clazz) {
		return baseDao.findListEntityBySql(sql, objects, clazz);
	}

	public Page<Map<String, Object>> findPageBySql(Pageable pageable, String sql, Object[] objects) {
		return baseDao.findPageBySql(pageable, sql, objects);
	}

	public List<Map<String, Object>> findListBySql(String sql, Object[] objects) {
		return baseDao.findListBySql(sql, objects);
	}

	public Map<String, Object> findOneBySql(String sql, Object[] objects) {
		return baseDao.findOneBySql(sql, objects);
	}

	public T findOneEntityBySql(String sql, Object[] objects, Class<T> clazz) {
		return baseDao.findOneEntityBySql(sql, objects, clazz);
	}

	@Override
	public int executeSql(String sql) {
		return baseDao.executeUpdateBySQL(sql);
	}

}
