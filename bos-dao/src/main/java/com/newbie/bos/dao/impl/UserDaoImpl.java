package com.newbie.bos.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.newbie.bos.dao.IUserDao;
import com.newbie.bos.dao.base.impl.BaseDaoImpl;
import com.newbie.bos.domain.User;



@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {
	/**
	 * 根据用户名和密码查询用户
	 */
	public User findUserByUsernameAndPassword(String username, String password) {
		String hql = "FROM User u WHERE u.username = ? AND u.password = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username,password);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	
	//更新用户用户密码，
	@Override
	public void execuUpdate(String queryName,Object... objects) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		int i=0;
		for (Object object : objects) {
			//为HQL中的？赋值
			query.setParameter(i++,object);
		}
		//执行更新数据
		query.executeUpdate();
	}
}
