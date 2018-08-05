package com.itheima.com.web.action;

import com.itheima.com.domain.User;
import com.itheima.com.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();
	private UserService userService;

	public String regist(){
        userService.regist(user);
        return "login";
	}
	public String login(){
		User existUser =userService.login(user);
		if(existUser==null){
			this.addActionError("对不起,用户名或者密码有错哦");
		    return "login";
		}else {
			ActionContext.getContext().getSession().put("existUser",existUser);
			return SUCCESS;
		}
	}


	@Override
	public User getModel() {
		return user;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
