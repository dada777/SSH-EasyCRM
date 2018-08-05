package com.itheima.com.dao;

import com.itheima.com.domain.User;

/*
*
*
* */
public interface UserDao extends BaseDao<User> {
	void save(User user);

	User login(User user);
}
