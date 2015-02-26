package com.wsun.repository.mapper.system;

import java.util.List;

import com.wsun.entity.system.Unit;
import com.wsun.repository.MyBatisRepository;

@MyBatisRepository
public interface UnitMapper {

	/**
	 * 通过主键ID删除
	 * 
	 * @param id
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * 新增Unit
	 * 
	 * @param unit
	 */
	int insert(Unit unit);

	/**
	 * 选择性新增Unit
	 * 
	 * @param unit
	 */
	int insertSelective(Unit unit);

	/**
	 * 通过主键ID查询Unit
	 * 
	 * @param id
	 * @return
	 */
	Unit selectByPrimaryKey(Long id);

	/**
	 * 选择性修改Unit
	 * 
	 * @param unit
	 */
	int updateByPrimaryKeySelective(Unit unit);

	/**
	 * 修改Unit
	 * 
	 * @param unit
	 */
	int updateByPrimaryKey(Unit unit);

	/**
	 * 查询所有机构
	 * 
	 * @return
	 */
	List<Unit> getAll();
}