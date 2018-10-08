package com.springboot.datasource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.datasource.dao.datasourceA.StudentMapper;
import com.springboot.datasource.dao.datasourceB.UseraMapper;
import com.springboot.datasource.dao.datasourceC.GradenMapper;

@RestController
public class DataSourceAController {
	
	@Autowired
	UseraMapper useraMapper;
	
	@Autowired
	StudentMapper studentMapper;
	
	@Autowired
	GradenMapper gradenMapper;
	
	@RequestMapping(value = "getDataA",method=RequestMethod.GET)
	public String dataSourceA(){
		
		return studentMapper.selectByPrimaryKey(1).getStudentName();
		
	}
	
	@RequestMapping(value = "getDataB",method=RequestMethod.GET)
	public int dataSourceB(){
		
		return useraMapper.selectByPrimaryKey(1).getAge();
		
	}
	
	@RequestMapping(value = "getDataC",method=RequestMethod.GET)
	public int dataSourceC(){
		
		return gradenMapper.selectByPrimaryKey(1).getFenshu();
		
	}
	
}
