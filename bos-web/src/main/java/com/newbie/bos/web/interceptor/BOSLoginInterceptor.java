package com.newbie.bos.web.interceptor;

import com.newbie.bos.domain.User;
import com.newbie.bos.utils.BOSUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 自定义拦截器，实现用未登登录时，跳转到登录页面
 * @author Administrator
 *
 */
public class BOSLoginInterceptor extends MethodFilterInterceptor {

	//拦截方法
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//从session中获得用户对象
		User user = BOSUtils.getLoginUser();
		if (user==null) {
			//用户没有登录
			return "login";
		}
		//放行
		return invocation.invoke();
	}

}
