package com.newbie.bos.web.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.newbie.bos.domain.Staff;
import com.newbie.bos.service.IStaffService;
import com.newbie.bos.utils.PageBean;
import com.newbie.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	//获取选择要删除的取派员ids
	private String ids;
	
	@Autowired
	private IStaffService staffService;
	
	public String pageQuery(){
		staffService.pageQuery(pageBean);
		java2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize"});
		return NONE;
	}
	
	/**
	 * 逻辑上删除     取派员
	 * @return
	 */
	public String deleteBatch(){
		staffService.deleteBatch(ids);
		return LIST;
	}

	//编辑取派员信息
	public String edit(){
		//查询数据库  ， 查询当前的取派员信息
		Staff staff = (Staff)staffService.findStaffById(model.getId());
		//更新编辑取派员的信息
		staff.setName(model.getName());
		staff.setTelephone(model.getTelephone());
		staff.setHaspda(model.getHaspda());
		staff.setStation(model.getStation());
		staff.setStandard(model.getStandard());
		
		//更新取派员数据表
		staffService.updateStaff(staff);
		
		return LIST;
	}
	



	public String getIds() {
		return ids;
	}



	public void setIds(String ids) {
		this.ids = ids;
	}

}
