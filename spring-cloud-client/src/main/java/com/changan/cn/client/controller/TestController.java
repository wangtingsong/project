package com.changan.cn.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.changan.cn.client.service.HelloR;

@RestController
public class TestController {
	
	@Autowired
	HelloR helloR;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String get(String name){
		
		return helloR.get(name);
	}
}
