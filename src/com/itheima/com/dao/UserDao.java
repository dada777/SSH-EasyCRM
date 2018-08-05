package com.itheima.com.dao;

import com.itheima.com.domain.User;

import java.util.List;

/*
*
*
* */
public interface UserDao extends BaseDao<User> {
	void save(User user);

	User login(User user);


}
