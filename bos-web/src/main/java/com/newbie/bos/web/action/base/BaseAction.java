package com.newbie.bos.web.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.newbie.bos.domain.Function;
import com.newbie.bos.model.ComboTreeModel;
import com.newbie.bos.utils.CombotreeDataFormaterUtils;
import com.newbie.bos.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
/**
 * 表现层通用实现
 * @author zhaoqx
 *
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	//视图结果集
	public static final String HOME = "home";
	public static final String LIST = "list";
	
	//分装查询分页数据
	public PageBean pageBean = new PageBean();
	DetachedCriteria detachedCriteria = null;
	
	//模型对象
	protected T model;
	
	private String listJson;
	
	
	public T getModel() {
		return model;
	}
	
	//在构造方法中动态获取实体类型，通过反射创建model对象
	public BaseAction() {
		//获取注解上的类s
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		
		//获得BaseAction上声明的泛型数组
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		Class<T> entityClass = (Class<T>) actualTypeArguments[0];
		
		//设置    创建    离线查询对象
		detachedCriteria = DetachedCriteria.forClass(entityClass);
		pageBean.setDetachedCriteria(detachedCriteria);
		
		//通过反射创建对象
		try {
			model = entityClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将java对象转换为json数据串
	 * 并返回到页面
	 * @param object
	 * @param excludes  排除不想要被转为json的键值域
	 */
	public void java2Json(Object object,String[] excludes){
		//使用json-lib工具，将pageBean对象写回页面
		//JSONObject---将单一对象转为json
		//JSONArray----将数组或者集合对象转为json
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		JSONObject json = JSONObject.fromObject(object, jsonConfig);
			
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 将java对象转换为json数据串
	 * 并返回到页面
	 * @param object
	 * @param excludes  排除不想要被转为json的键值域
	 */
	public void java2Json(List objects,String[] excludes){
		//使用json-lib工具，将pageBean对象写回页面
		//JSONObject---将单一对象转为json
		//JSONArray----将数组或者集合对象转为json
		Object model = objects.get(0);
		if (model instanceof Function ) {
			List<ComboTreeModel> comboTreeModels = new ArrayList<>();
			List<Function> functions = objects;
			CombotreeDataFormaterUtils.modelFormatCombotree(comboTreeModels, functions);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(excludes);
			listJson = JSONArray.fromObject(comboTreeModels, jsonConfig).toString();
		}else {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(excludes);
			listJson = JSONArray.fromObject(objects, jsonConfig).toString();
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(listJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * 注入page
	 * @param page属性（当前的页码数）和rows（当前页面要显示的记录数）属性
	 */
	public void setPage(String page) {
		pageBean.setCurrentPage(page);
	}
	public void setRows(String rows) {
		pageBean.setPageSize(rows);
	}

}
