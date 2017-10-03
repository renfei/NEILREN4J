package com.neilren.neilren4j.common.dao;

import com.neilren.neilren4j.common.entity.SendEmailLog;
import com.neilren.neilren4j.common.persistence.CrudDao;
import com.neilren.neilren4j.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface SendEmailLogDao extends CrudDao<SendEmailLog> {
    int insert(SendEmailLog sendEmailLog);
}
