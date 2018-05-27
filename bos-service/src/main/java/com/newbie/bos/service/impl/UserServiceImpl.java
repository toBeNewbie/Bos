package com.newbie.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newbie.bos.dao.IUserDao;
import com.newbie.bos.domain.User;
import com.newbie.bos.service.IUserService;
import com.newbie.bos.utils.MD5Utils;


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
	
}
