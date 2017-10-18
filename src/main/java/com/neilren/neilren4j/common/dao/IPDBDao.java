package com.neilren.neilren4j.common.dao;

import com.neilren.neilren4j.common.entity.IPDBObject;
import com.neilren.neilren4j.common.persistence.CrudDao;
import com.neilren.neilren4j.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface IPDBDao extends CrudDao<IPDBObject> {
    int inserts(@Param("list")List<IPDBObject> ipdbObjectList);
    void cloneTable();
    void rename();
    IPDBObject selectByIP(String IP);
}
