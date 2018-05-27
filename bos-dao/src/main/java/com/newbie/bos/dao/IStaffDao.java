package com.newbie.bos.dao;

import com.newbie.bos.dao.base.IBaseDao;
import com.newbie.bos.domain.Staff;

public interface IStaffDao extends IBaseDao<Staff>{
	void executeUpdate(String updateName, String id);
}
