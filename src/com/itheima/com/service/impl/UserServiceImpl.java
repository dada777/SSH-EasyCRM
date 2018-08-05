package com.itheima.com.service.impl;

import com.itheima.com.dao.UserDao;
import com.itheima.com.dao.impl.UserDaoImpl;
import com.itheima.com.domain.User;
import com.itheima.com.service.UserService;
import com.itheima.com.utils.MD5Utils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public void regist(User user) {
		//System.out.println(user);
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
	    user.setUser_state("1");
	    userDao.save(user);
	}

	@Override
	public User login(User user) {
        user.setUser_password(MD5Utils.md5(user.getUser_password()));
		return userDao.login(user);
	}

	@Override
	public List<User> findAllUser() {
	 //由于Base 里有 findAll 方法 无需重新定义咯
		return userDao.findAll();
	}

}
