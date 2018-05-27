package com.newbie.bos.service;

import com.newbie.bos.domain.User;

public interface IUserService {

	public User login(User model);

	public void editUserPassword(String id,String password);
}
