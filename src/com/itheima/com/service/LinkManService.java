package com.itheima.com.service;

import com.itheima.com.domain.LinkMan;
import com.itheima.com.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;


public interface LinkManService {
	PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);


	//添加联系人，确定所属客户
	/**
	 * 
	 * <p>Title: insertLinkman</p>
	 * <p>Description: </p>
	 * @param custId 所属客户id
	 * @param cstLinkman 联系人信息
	 */

	//更新联系人

	//删除联系人
	//根据联系人id查询联系人
	//查询联系人列表总记录数
	//查询联系人列表
}
