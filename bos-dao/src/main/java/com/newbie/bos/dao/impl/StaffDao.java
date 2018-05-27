package com.newbie.bos.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.newbie.bos.dao.IStaffDao;
import com.newbie.bos.dao.base.impl.BaseDaoImpl;
import com.newbie.bos.domain.Staff;

@Repository
public class StaffDao extends BaseDaoImpl<Staff> implements IStaffDao {

	@Override
	public void executeUpdate(String updateName, String id) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery("staff.delete");
		query.setParameter(0, id);
		query.executeUpdate();
	}

}
