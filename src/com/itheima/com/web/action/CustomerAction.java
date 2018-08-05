package com.itheima.com.web.action;

import com.itheima.com.domain.Customer;
import com.itheima.com.domain.User;
import com.itheima.com.service.CustomerService;
import com.itheima.com.service.UserService;
import com.itheima.com.utils.PageBean;
import com.itheima.com.utils.UploadUtils;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.File;
import java.io.IOException;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	private Customer customer = new Customer();
	private CustomerService customerService;


	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public Customer getModel() {
		return customer;
	}

    public String saveUI(){

		return "saveUI";
	}

	/*
	* 保存图片和东西
	* 增 步骤 --需要同时保存图片
	* 文件上	* */
	public String save() throws IOException {

		if(upload!=null){
			//设置文件的路径
			String path="E:/upload";
			//弄一个随机名字
            String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
            //目录分离
			String realPath=UploadUtils.getPath(uuidFileName);
			String url = path+realPath;
			File file = new File(url);
			if(!file.exists()){
               file.mkdirs();
			}
				File dictFile = new File(url+"/"+uuidFileName);
		FileUtils.copyFile(upload,dictFile);
		customer.setCust_image(url+"/"+uuidFileName);
	}

		customerService.save(customer);
		return "saveSuccess";
	}



	/*
	* 删
	*先查,删除图片//
	* */
   public String delete(){
   	  customer= customerService.findById(customer.getCust_id());
   	  if(customer.getCust_image()!=null){
   	  	File file = new File(customer.getCust_image());
   	  	if(file.exists()) {
			file.delete();
		}
	  }
     customerService.delete(customer);
   	  return "deleteSuccess";
   }

   /*
   *      修改   第一步
   * 跳转到修改页面 先把页面的东西给他回显出来
   * 通过ID 的方式去查
   * 客户..
   *
   *
   * */
   public  String edit(){
     customer =customerService.findById(customer.getCust_id());
    // ActionContext.getContext().getValueStack().push(customer);
    return "editSuccess";
   }
   /*
   *  改 第二步 进行UPDATE 操作
   * */
   public String update() throws IOException {
	   if (upload != null) {
		   // 已经选择了:
		   // 删除原有文件:
		   String cust_image = customer.getCust_image();
		   if (cust_image != null || !"".equals(cust_image)) {
			   File file = new File(cust_image);
			   file.delete();
		   }
		   // 文件上传：
		   // 设置文件上传路径:
		   String path = "E:/upload";
		   // 一个目录下存放的相同文件名：随机文件名
		   String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
		   // 一个目录下存放的文件过多：目录分离
		   String realPath = UploadUtils.getPath(uuidFileName);
		   // 创建目录:
		   String url = path + realPath;
		   File file = new File(url);
		   if (!file.exists()) {
			   file.mkdirs();
		   }
		   // 文件上传:
		   File dictFile = new File(url + "/" + uuidFileName);
		   FileUtils.copyFile(upload, dictFile);

		   customer.setCust_image(url + "/" + uuidFileName);
	   }
	   customerService.update(customer);
	   return "updateSuccess";
   }

   /*
	 *
	 *
	 * 查步骤    统一查询
	 * 这一步是用于Page的设置
	 * 页面返回PAGE 的时候调用封装 FindAll 的时候用的  跟着findAll
	 * */
	private String uploadFileName;//文件名称
	private String uploadContentType;//文件类型
	private File upload;  //文件..
	private Integer currPage =1;
	public void setCurrPage(Integer currPage) {
		if(currPage == null){
			currPage = 1;
		}
		this.currPage = currPage;
	}
	private Integer pageSize =3;

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String findAll(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		// 调用业务层查询:   多条件查询
		if (customer.getCust_name() != null) {
			// 输入名称:
			detachedCriteria.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
		}


		//提示 如果客户没有任何选择 此时 -请选择这个也不是默认选上的,传到后台就是没有这个字段 字段为NULL-
		//先判断客户有没有选择了设么东西 在这行有没有按了什么东西
		//第一步 判断客户 有没有做选择 (需要判断客户的ID为多少 )
		// 判断ID 前 有没有生成了 DictSource 没生成就空指针..

		// 第二步  看ID 是多少 如果是"" 为请选择 无需任何操作  (排除"" 选择)

		if (customer.getBaseDictSource() != null) {


			if (customer.getBaseDictSource().getDict_id() != null
					&& !"".equals(customer.getBaseDictSource().getDict_id())) {
				detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
			}

		}
		if (customer.getBaseDictLevel() != null) {
			if (customer.getBaseDictLevel().getDict_id() != null
					&& !"".equals(customer.getBaseDictLevel().getDict_id())) {
				detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
			}
		}
		if (customer.getBaseDictIndustry() != null && customer.getBaseDictIndustry().getDict_id() != null) {
			if (customer.getBaseDictIndustry().getDict_id() != null
					&& !"".equals(customer.getBaseDictIndustry().getDict_id())) {
				detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
			}
		}
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}
}

