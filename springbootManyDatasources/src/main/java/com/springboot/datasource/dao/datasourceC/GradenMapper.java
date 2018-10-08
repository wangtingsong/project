package com.springboot.datasource.dao.datasourceC;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springboot.datasource.entity.Graden;
import com.springboot.datasource.entity.GradenExample;

public interface GradenMapper {
    long countByExample(GradenExample example);

    int deleteByExample(GradenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Graden record);

    int insertSelective(Graden record);

    List<Graden> selectByExample(GradenExample example);

    Graden selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Graden record, @Param("example") GradenExample example);

    int updateByExample(@Param("record") Graden record, @Param("example") GradenExample example);

    int updateByPrimaryKeySelective(Graden record);

    int updateByPrimaryKey(Graden record);
}