package com.neilren.neilren4j.dao;

import com.neilren.neilren4j.dbentity.TWorstPasswd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TWorstPasswdMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TWorstPasswd record);

    TWorstPasswd selectByPrimaryKey(Long id);

    TWorstPasswd selectByPwd(@Param("pwd") String pwd);

    List<TWorstPasswd> selectAll();

    int updateByPrimaryKey(TWorstPasswd record);
}