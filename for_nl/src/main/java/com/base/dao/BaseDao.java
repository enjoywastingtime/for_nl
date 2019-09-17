package com.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.base.entity.BaseEntity;

@NoRepositoryBean
public interface BaseDao<T extends BaseEntity, ID extends Serializable> extends JpaRepository<T, ID> {

	public List<Map<String, Object>> findListBySql(String sql, Object[] objects);

	public Map<String, Object> findOneBySql(String sql, Object[] objects);

	Page<Map<String, Object>> findPageBySql(Pageable pageable, String sql, Object[] objects);

	public List<T> findListEntityBySql(String sql, Object[] objects);

	public T findOneEntityBySql(String sql, Object[] objects);

	Page<T> findPageEntityBySql(Pageable pageable, String sql, Object[] objects);

}
