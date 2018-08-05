package com.itheima.com.dao;

import com.itheima.com.domain.BaseDict;

import java.util.List;

public interface BaseDictDao {
	List<BaseDict> findByTypeCode(String dict_type_code);
}
