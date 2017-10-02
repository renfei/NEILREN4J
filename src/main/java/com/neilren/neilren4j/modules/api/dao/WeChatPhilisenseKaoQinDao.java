package com.neilren.neilren4j.modules.api.dao;

import com.neilren.neilren4j.common.persistence.CrudDao;
import com.neilren.neilren4j.common.persistence.annotation.MyBatisDao;
import com.neilren.neilren4j.modules.api.entity.PhilisenseTable;

/**
 * Created by neil on 10/07/2017.
 */
@MyBatisDao
public interface WeChatPhilisenseKaoQinDao extends CrudDao<PhilisenseTable> {
    int insert(PhilisenseTable philisenseTable);

    PhilisenseTable selectByWeChat(String wechat_name);

    void deleteByWeChat(String wechat_name);
}
