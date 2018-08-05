package com.itheima.com.service.impl;

import com.itheima.com.dao.CustomerDao;
import com.itheima.com.domain.Customer;
import com.itheima.com.service.CustomerService;
import com.itheima.com.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<Customer>pageBean = new PageBean<>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		// 封装当前页数:
		Integer totalCount = customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 封装每页显示记录数:
		// 封装总记录数:
		// 调用DAO:
		double tc = totalCount;
		Double num = Math.ceil(tc /pageSize);
		pageBean.setTotalPage(num.intValue());
		Integer begin = (currPage - 1) * pageSize;

		List<Customer>list = customerDao.findByPage(detachedCriteria,begin,pageSize);
		// 封装总页数:
		pageBean.setList(list);
		// 封装每页显示数据的集合
		return pageBean;
	}

	@Override
	public Customer findById(Long cust_id) {

		return customerDao.findById(cust_id);
	}

	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}


}
