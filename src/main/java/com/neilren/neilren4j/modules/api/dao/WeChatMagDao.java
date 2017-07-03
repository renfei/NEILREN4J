package com.neilren.neilren4j.modules.api.dao;

import com.neilren.neilren4j.common.persistence.CrudDao;
import com.neilren.neilren4j.common.persistence.annotation.MyBatisDao;
import com.neilren.neilren4j.modules.api.entity.WeChatMsg;

@MyBatisDao
public interface WeChatMagDao extends CrudDao<WeChatMsg> {
    int insert(WeChatMsg weChatMsg);
}
