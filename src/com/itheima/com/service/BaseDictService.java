package com.itheima.com.service;

import com.itheima.com.domain.BaseDict;

import java.util.List;

public interface BaseDictService  {
	List<BaseDict> findByTypeCode(String dict_type_code);
}
