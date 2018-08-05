package com.itheima.com.service.impl;

import com.itheima.com.dao.BaseDictDao;
import com.itheima.com.domain.BaseDict;
import com.itheima.com.service.BaseDictService;

import java.util.List;

public class BaseDictServiceImpl implements BaseDictService {
     private BaseDictDao baseDictDao ;

	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return		baseDictDao.findByTypeCode( dict_type_code);
	}
}
