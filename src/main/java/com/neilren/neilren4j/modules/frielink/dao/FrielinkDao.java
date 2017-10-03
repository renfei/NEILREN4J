package com.neilren.neilren4j.modules.frielink.dao;

import com.neilren.neilren4j.common.persistence.CrudDao;
import com.neilren.neilren4j.common.persistence.annotation.MyBatisDao;
import com.neilren.neilren4j.modules.frielink.entity.Frielink;

import java.util.List;

@MyBatisDao
public interface FrielinkDao extends CrudDao<Frielink> {
    List<Frielink> selectValidFrieLinks();
    List<Frielink> selectBlackFrieLinks();
    List<Frielink> selectShowingFrieLinks();
    List<Frielink> selectHideFrieLinks();
    Frielink selectFrieLinkByDomain(String doamin);
    Frielink selectFrieLinkByLink(String link);
    int insertFrieLink(Frielink frielink);
    int updateByFrieLink(Frielink frieLink);
}
