package com.neilren.neilren4j.dao;

import com.neilren.neilren4j.dbentity.THoliday;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface THolidayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(THoliday record);

    THoliday selectByPrimaryKey(Long id);

    THoliday selectHolidayByDate(@Param("start_date") Date start_date, @Param("end_date") Date end_date);

    List<THoliday> selectAll();

    int updateByPrimaryKey(THoliday record);
}