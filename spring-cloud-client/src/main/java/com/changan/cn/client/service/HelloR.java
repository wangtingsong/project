package com.changan.cn.client.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "producer-2", fallback = HystrixTest.class)
public interface HelloR {
	@RequestMapping(value = "/pro/test", method = RequestMethod.POST)
	public String get(@RequestParam(value ="name") String name);
}
