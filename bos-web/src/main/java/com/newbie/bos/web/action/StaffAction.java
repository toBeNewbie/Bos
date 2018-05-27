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
	
	//属性驱动，接收请求参数
	private String page;//当前页数
	private String rows;//当前也要显示的记录数
	
	//获取选择要删除的取派员ids
	private String ids;
	
	

	@Autowired
	private IStaffService staffService;
	
	public String pageQuery() throws IOException{
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(page);
		pageBean.setPageSize(String.valueOf(rows));
		
		//创建离线提交查询对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		pageBean.setDetachedCriteria(detachedCriteria);
		
		staffService.pageQuery(pageBean);
		
		//使用json-lib工具，将pageBean对象写回页面
		//JSONObject---将单一对象转为json
		//JSONArray----将数组或者集合对象转为json
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"currentPage","detachedCriteria","pageSize"});
		JSONObject json = JSONObject.fromObject(pageBean, jsonConfig);
		
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
		
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
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}



	public String getIds() {
		return ids;
	}



	public void setIds(String ids) {
		this.ids = ids;
	}

}
