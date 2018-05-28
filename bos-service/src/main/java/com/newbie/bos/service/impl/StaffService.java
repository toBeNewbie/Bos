package com.newbie.bos.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newbie.bos.dao.IStaffDao;
import com.newbie.bos.domain.Staff;
import com.newbie.bos.service.IStaffService;
import com.newbie.bos.utils.PageBean;

/**
 * 获得取派员staff数据
 * @author Administrator
 *
 */
@Service
@Transactional
public class StaffService implements IStaffService {

	@Autowired
	private IStaffDao staffDao;
	
	@Override
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
	}

	/**
	 * 取派员批量删除
	 * 逻辑删除 把deltag置为 “1”
	 */
	@Override
	public void deleteBatch(String ids) {
		if (StringUtils.isNotBlank(ids)) {
			String[] listIds = ids.split(",");
			for (String id : listIds) {
				staffDao.executeUpdate("staff.delete",id);
			}
		}
	}

	@Override
	public Staff findStaffById(String id) {
		Staff staff = staffDao.findById(id);
		return staff;
	}

	@Override
	public void updateStaff(Staff staff) {
		// TODO Auto-generated method stub
		staffDao.saveOrUpdate(staff);
	}

}
