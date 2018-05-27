package com.newbie.bos.dao.base.impl;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.newbie.bos.dao.base.IBaseDao;
import com.newbie.bos.domain.Staff;
import com.newbie.bos.utils.PageBean;
/**
 * 持久层通用实现
 * @author zhaoqx
 *
 * @param <T>
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
	//代表的是某个实体的类型
	private Class<T> entityClass;
	
	@Resource//根据类型注入spring工厂中的会话工厂对象sessionFactory
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	//在父类（BaseDaoImpl）的构造方法中动态获得entityClass
	public BaseDaoImpl() {
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得父类上声明的泛型数组
		Type[] actualTypeArguments = superclass.getActualTypeArguments();
		entityClass = (Class<T>) actualTypeArguments[0];
	}
	
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}
	
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}
	
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	public T findById(Serializable id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	public List<T> findAll() {
		String hql = "FROM " + entityClass.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}

	public void saveOrUpdate(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}
	
	
	/**
	 * 公用的分页方法
	 */
	public void pageQuery(PageBean pageBean){
		String currentPage = pageBean.getCurrentPage();
		String pageSize = pageBean.getPageSize();
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
	
		//查询总数据量
		//指定hibernate框架发出sql的形式----》select count(*) from bc_staff;
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		//获得总记录数
		Long totalSize = list.get(0);
		pageBean.setTotal(totalSize.toString());
		
		//查询rows---当前页需要展示的数据集合
		//指定hibernate框架发出sql的形式----》select * from bc_staff;
		detachedCriteria.setProjection(null);
		int firstResult = (Integer.parseInt(currentPage)-1)*Integer.parseInt(pageSize);
		int maxResults=Integer.parseInt(pageSize);
		List rows = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
		pageBean.setRows(rows);
	}
}
