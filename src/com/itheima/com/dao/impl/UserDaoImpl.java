package com.itheima.com.dao.impl;

import com.itheima.com.dao.UserDao;
import com.itheima.com.domain.User;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User login(User user) {
	List<User> list= (List<User>) this.getHibernateTemplate().find("from User where user_code=? and user_password=?",user.getUser_code(),user.getUser_password());
		if(list.size()>0){
			return list.get(0);
		}
	return null;
	}


}
