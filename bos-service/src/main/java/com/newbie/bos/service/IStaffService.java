package com.newbie.bos.service;

import com.newbie.bos.domain.Staff;
import com.newbie.bos.utils.PageBean;

public interface IStaffService {

	public void pageQuery(PageBean pageBean);

	public void deleteBatch(String ids);

	public Staff findStaffById(String id);

	public void updateStaff(Staff staff);

}
