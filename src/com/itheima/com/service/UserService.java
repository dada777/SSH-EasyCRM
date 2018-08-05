package com.itheima.com.service;

import com.itheima.com.domain.User;

import java.util.List;

public interface UserService {
	void regist(User user);

	User login(User user);

	List<User> findAllUser();
}
