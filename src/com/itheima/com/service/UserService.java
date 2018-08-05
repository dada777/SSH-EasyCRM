package com.itheima.com.service;

import com.itheima.com.domain.User;

public interface UserService {
	void regist(User user);

	User login(User user);
}
