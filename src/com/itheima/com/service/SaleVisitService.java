package com.itheima.com.service;

import com.itheima.com.domain.SaleVisit;
import com.itheima.com.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;


import com.itheima.com.domain.SaleVisit;

/**
 * 客户拜访记录的业务层的接口
 * @author jt
 *
 */
public interface SaleVisitService {

	PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(SaleVisit saleVisit);

}
