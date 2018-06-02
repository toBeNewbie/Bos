package com.newbie.bos.service;

import com.newbie.bos.domain.User;
import com.newbie.bos.utils.PageBean;

public interface IUserService {

	public User login(User model);

	public void editUserPassword(String id,String password);

	public void save(User model, String[] roleIds);

	public void pageQuery(PageBean pageBean);
}
