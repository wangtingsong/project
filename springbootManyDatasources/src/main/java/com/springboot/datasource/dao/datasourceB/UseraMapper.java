package com.springboot.datasource.dao.datasourceB;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springboot.datasource.entity.Usera;
import com.springboot.datasource.entity.UseraExample;

public interface UseraMapper {
    long countByExample(UseraExample example);

    int deleteByExample(UseraExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Usera record);

    int insertSelective(Usera record);

    List<Usera> selectByExample(UseraExample example);

    Usera selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Usera record, @Param("example") UseraExample example);

    int updateByExample(@Param("record") Usera record, @Param("example") UseraExample example);

    int updateByPrimaryKeySelective(Usera record);

    int updateByPrimaryKey(Usera record);
}