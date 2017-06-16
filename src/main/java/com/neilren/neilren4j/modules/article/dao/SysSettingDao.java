package com.neilren.neilren4j.modules.article.dao;

import com.neilren.neilren4j.common.persistence.CrudDao;
import com.neilren.neilren4j.common.persistence.annotation.MyBatisDao;
import com.neilren.neilren4j.modules.article.entity.SysSetting;

@MyBatisDao
public interface SysSettingDao extends CrudDao<SysSetting> {
    int deleteByPrimaryKey(Long id);

    int insert(SysSetting record);

    int insertSelective(SysSetting record);

    SysSetting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysSetting record);

    int updateByPrimaryKeyWithBLOBs(SysSetting record);

    int updateByPrimaryKey(SysSetting record);
}