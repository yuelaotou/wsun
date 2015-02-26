/**
 * 
 */
package com.wsun.service.system;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsun.common.exception.BusinessException;
import com.wsun.common.utils.Digests;
import com.wsun.common.utils.Encodes;
import com.wsun.entity.system.User;
import com.wsun.entity.system.UserRole;
import com.wsun.repository.mapper.system.UserMapper;
import com.wsun.repository.mapper.system.UserRoleMapper;

@Service
public class UserService {

	private static final Logger logger = LogManager.getLogger(UserService.class);
	public static final String HASH_ALGORITHM = "SHA-1";

	public static final int HASH_INTERATIONS = 1024;

	private static final int SALT_SIZE = 8;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	public User getByPrimaryKey(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过登录用户名查询用户
	 * 
	 * @param loginName
	 * @return
	 */
	public User getByLoginName(String loginName) {
		return userMapper.selectByLoginName(loginName);
	}

	/**
	 * 根据主键id查询带角色的用户信息
	 * 
	 * @param id
	 * @return
	 */
	public User getUserDetailById(Long id) {
		logger.info("根据主键id查询带角色的用户信息，id：{}", id);
		return userMapper.getUserDetailById(id);
	}

	/**
	 * 根据登录名查询角色和资源列表
	 * 
	 * @param loginName
	 * @return
	 */
	public User getRoleAndResourceByLoginName(String loginName) {
		return userMapper.getRoleAndResourceByLoginName(loginName);
	}

	/**
	 * 查询所有带资源信息的角色信息
	 * 
	 * @return
	 */
	public List<User> getUserList(Map<String, Object> parameterMap) {
		return userMapper.getUserList(parameterMap);
	}

	/**
	 * 查询所有带资源信息的角色信息
	 * 
	 * @return
	 */
	public int getUserListCount(User user) {
		return userMapper.getUserListCount(user);
	}

	public void saveUser(User user) {
		// 查询当前系统中相同登录名的用户
		User u = userMapper.selectByLoginName(user.getLoginName());

		if (user.getId() == null) {
			if (u != null) {
				throw new BusinessException("系统存在登录名为[" + user.getLoginName() + "]的用户，不允许重复添加！");
			}
			// logger.info("新增用户：" + JsonMapper.nonEmptyMapper().toJson(user));
			if (StringUtils.isNotBlank(user.getPlainPassword())) {
				entryptPassword(user);
			}
			user.setStatus(1);
			user.setCreateDate(new Date());
			userMapper.insert(user);
		} else {
			// 如果id不同，则说明系统中存在修改后登录名的用户
			if (u.getId() != user.getId()) {
				throw new BusinessException("系统存在登录名为[" + user.getLoginName() + "]的用户，请更改！");
			}
			if (!u.getPassword().equals(user.getPassword())) {
				entryptPassword(user);
			}
			// logger.info("修改用户：" + JsonMapper.nonEmptyMapper().toJson(user));
			userMapper.updateByPrimaryKeySelective(user);
		}

		// 删除用户已经分配的角色信息
		userRoleMapper.deleteByUserId(user.getId());

		// 插入授权的角色信息
		if (StringUtils.isNotEmpty(user.getRoles())) {
			String[] roles = StringUtils.split(user.getRoles(), ",");
			Arrays.sort(roles);
			logger.info("登录名：{}，插入角色信息：{}", user.getName(), roles);
			UserRole userRole = null;
			for (String res : roles) {
				userRole = new UserRole();
				userRole.setUserId(user.getId());
				userRole.setRoleId(Long.parseLong(res));
				userRoleMapper.insert(userRole);
			}
		}
	}

	/**
	 * 通过id删除用户
	 * 
	 * @param id
	 */
	public void deleteUser(Long id) {
		// 删除用户
		userMapper.deleteByPrimaryKey(id);
		// 删除用户对应的角色信息
		userRoleMapper.deleteByUserId(id);
	}

	/**
	 * 更新用户状态
	 * 
	 * @param id
	 * @param status
	 */
	public void updateUserStatus(Long id, Integer status) {
		User user = new User();
		user.setId(id);
		user.setStatus(status);
		userMapper.updateByPrimaryKeySelective(user);
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

}
