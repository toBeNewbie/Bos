package com.newbie.bos.dao;

import com.newbie.bos.dao.base.IBaseDao;
import com.newbie.bos.domain.User;

public interface IUserDao extends IBaseDao<User> {

	public User findUserByUsernameAndPassword(String username, String password);

	public void execuUpdate(String queryName,Object... objects);

}
