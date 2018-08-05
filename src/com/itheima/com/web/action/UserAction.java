package com.itheima.com.web.action;

import com.itheima.com.domain.User;
import com.itheima.com.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.List;

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


	//查询所有的用户 下拉列表 不需要分页
	public String findAllUser() throws IOException {
    List<User> list =  userService.findAllUser();

    //转化成Jason格式 异步异步走
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
       //不需要传递 List  只有穿个Jason格式的东西过去就行了

		return  NONE;
	}

	@Override
	public User getModel() {
		return user;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
