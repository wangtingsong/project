package com.changan.cn.client.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class HystrixTest implements HelloR{

	@Override
	public String get(@RequestParam(value = "name")String name) {
		// TODO Auto-generated method stub
		return "this request is false"+name;
	}

}
