package com.itheima.com.service;

import com.itheima.com.domain.Customer;
import com.itheima.com.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerService {

	void save(Customer customer);
	PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	Customer findById(Long cust_id);

	void delete(Customer customer);

	void update(Customer customer);

	List<Customer> findAll();


	List<Customer> findAllCustomer();
}
