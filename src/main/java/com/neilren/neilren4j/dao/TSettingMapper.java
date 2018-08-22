package com.neilren.neilren4j.dao;

import com.neilren.neilren4j.dbentity.TSetting;
import java.util.List;

public interface TSettingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TSetting record);

    TSetting selectByPrimaryKey(Long id);

    List<TSetting> selectAll();

    List<TSetting> selectByKey(String key);

    int updateByPrimaryKey(TSetting record);
}