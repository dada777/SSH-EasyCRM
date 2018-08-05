package com.itheima.com.web.action;

import com.itheima.com.domain.Customer;
import com.itheima.com.domain.LinkMan;
import com.itheima.com.service.CustomerService;
import com.itheima.com.service.LinkManService;
import com.itheima.com.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	private LinkMan linkMan = new LinkMan();
	private LinkManService linkManService;
	// 注入客户管理的Service  SET方法一律放尾部  LMS 和CTS 已放置  MODLE 已设置
	private CustomerService customerService;

	/*
	* 增 先调整 UI  由于是需要放置CUSTOMER 的 先顺带吧CUSTOMER 放进去
	* 第二步 增加 操作哦
	* */
	public String saveUI(){
		List<Customer> list= customerService.findAll();
		ActionContext.getContext().getValueStack().set("list",list);
		return "saveUI";
	}
	public String save(){
		// 调用业务层保存联系人
		linkManService.save(linkMan);
		return "saveSuccess";
	}

	/*
   * 删除步骤      通过ID 得到对象删除它
   * */
	public String delete(){
		linkMan = linkManService.findById(linkMan.getLkm_id());
		linkManService.delete(linkMan);
		return "deleteSuccess";
	}

	/*
  * 改  1 先封装EDIT 把东西查出来放置到EDIT 页面
  * */
	public String edit(){
		List<Customer> list = customerService.findAll();
		// 根据id查询联系人:
		linkMan = linkManService.findById(linkMan.getLkm_id());
		// 将list和linkMan的对象带到页面上：
		ActionContext.getContext().getValueStack().set("list", list);
		ActionContext.getContext().getValueStack().push(linkMan);
		return "editSuccess";
	}

	/*
	* 二 执行 UPDATE 操作
	* */
	public String update(){
		// 调用业务层:
		linkManService.update(linkMan);
		return "updateSuccess";
	}

	/*
*  查询操作
*  先 PAGE  再 FINDALL
*
* */
	// 分页参数:
	private Integer currPage = 1;
	private Integer pageSize = 3;
	public void setCurrPage(Integer currPage) {
		if(currPage == null){
			currPage = 1;
		}
		this.currPage = currPage;
	}
	public void setPageSize(Integer pageSize) {
		if(pageSize == null){
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}
	public String findAll(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		// 创建离线条件查询:
		// 设置条件
		if(linkMan.getLkm_name()!=null){
			// 设置按名称查询的条件:
			detachedCriteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		// 设置性别条件:
		if(linkMan.getLkm_gender() != null && !"".equals(linkMan.getLkm_gender())){
			// 设置按性别查询的条件:
			detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		PageBean<LinkMan> pageBean = linkManService.findAll(detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}


	@Override
	public LinkMan getModel() {
		return linkMan;
	}

	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
}
