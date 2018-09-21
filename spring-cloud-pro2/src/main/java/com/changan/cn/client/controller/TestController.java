package com.changan.cn.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@RequestMapping(value = "/pro/test", method = RequestMethod.POST)
	public String get(@RequestParam String name){
		return "2"+name;
	}
}
