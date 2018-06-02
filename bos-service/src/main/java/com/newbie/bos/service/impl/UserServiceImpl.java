package com.newbie.bos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newbie.bos.dao.IUserDao;
import com.newbie.bos.domain.Role;
import com.newbie.bos.domain.User;
import com.newbie.bos.service.IUserService;
import com.newbie.bos.utils.MD5Utils;
import com.newbie.bos.utils.PageBean;


@Service
@Transactional
public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserDao userDao;
	/***
	 * 用户登录
	 */
	public User login(User user) {
		//使用MD5加密密码
		String password = MD5Utils.md5(user.getPassword());
		return userDao.findUserByUsernameAndPassword(user.getUsername(),password);
	}
	
	//修改用户密码
	@Override
	public void editUserPassword(String id,String password) {
		password= MD5Utils.md5(password);
		userDao.execuUpdate("user.editPassword",password,id);
	}

	@Override
	public void save(User user, String[] roleIds) {
		String password = MD5Utils.md5(user.getPassword());
		user.setPassword(password);
		userDao.save(user);
		if (roleIds!=null&&roleIds.length>0) {
			for (String roleId : roleIds) {
				//手动构造托管对象
				Role role=new Role(roleId);
				//用户关联角色对象
				user.getRoles().add(role);
			}
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		userDao.pageQuery(pageBean);
	}


	
}
