package com.wsun.repository.mapper.system;

import java.util.List;
import java.util.Map;

import com.wsun.entity.system.Resource;
import com.wsun.repository.MyBatisRepository;

@MyBatisRepository
public interface ResourceMapper {

	/**
	 * 通过主键ID删除
	 * 
	 * @param id
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * 新增Resource
	 * 
	 * @param resource
	 */
	int insert(Resource resource);

	/**
	 * 选择性新增Resource
	 * 
	 * @param resource
	 */
	int insertSelective(Resource resource);

	/**
	 * 通过主键ID查询Resource
	 * 
	 * @param id
	 * @return
	 */
	Resource selectByPrimaryKey(Long id);

	/**
	 * 选择性修改Resource
	 * 
	 * @param resource
	 */
	int updateByPrimaryKeySelective(Resource resource);

	/**
	 * 修改Resource
	 * 
	 * @param resource
	 */
	int updateByPrimaryKey(Resource resource);

	/**
	 * 查询根结点的资源
	 * 
	 * @return
	 */
	List<Resource> getParents(Map<String, Object> parameters);

	/**
	 * 查询子节点的资源
	 * 
	 * @param id
	 * @return
	 */
	List<Resource> getChildrenById(Map<String, Object> parameters);

	/**
	 * 查询所有资源
	 * 
	 * @return
	 */
	List<Resource> getAll();

	/**
	 * 通过parentID删除
	 * 
	 * @param id
	 */
	int deleteByParentId(Long id);

}