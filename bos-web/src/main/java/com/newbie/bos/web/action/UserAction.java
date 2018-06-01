package com.newbie.bos.web.action;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.newbie.bos.domain.User;
import com.newbie.bos.service.IUserService;
import com.newbie.bos.utils.BOSUtils;
import com.newbie.bos.utils.MD5Utils;
import com.newbie.bos.web.action.base.BaseAction;


@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	//属性驱动，接收页面输入的验证码
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 用户登录  使用shiro框架提供的方法进行认证
	 */
	public String login(){
		//从Session中获取生成的验证码
		String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		//校验验证码是否输入正确
		if(StringUtils.isNotBlank(checkcode) && checkcode.equals(validatecode)){
			//输入的验证码正确
			//使用shiro提供的方法进行认证
			Subject subject = SecurityUtils.getSubject();//获得当前用户登录对象，现在状态为“未认证”
			//用户密码令牌
			UsernamePasswordToken token = new UsernamePasswordToken(model.getUsername(), MD5Utils.md5(model.getPassword()));
			try {
				subject.login(token);;
				User user = (User) subject.getPrincipal();
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
			} catch (AuthenticationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return LOGIN;
			}
			return HOME;
		}else{
			//输入的验证码错误,设置提示信息，跳转到登录页面
			this.addActionError("输入的验证码错误！");
			return LOGIN;
		}
	}
	
	//修改密码
	public String editPassword() throws IOException{
		
		String tag = "1";
		
		//获取当前登录的用户
		User user = BOSUtils.getLoginUser();
		try {
 			userService.editUserPassword(user.getId(),model.getPassword());
		} catch (Exception e) {
			tag = "0";
			e.printStackTrace();
		}
		
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(tag);
		return NONE;
	}
	
	//退出登录
	public String logout(){
		//销毁session用户数据
		BOSUtils.getSession().invalidate();
		return LOGIN;
	}
	
}
