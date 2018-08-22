package com.neilren.neilren4j.dao;

import com.neilren.neilren4j.dbentity.TFrielink;
import java.util.List;

public interface TFrielinkMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TFrielink record);

    TFrielink selectByPrimaryKey(Long id);

    List<TFrielink> selectAll();

    int updateByPrimaryKey(TFrielink record);

    /////
    List<TFrielink> selectValidFrieLinks();
    List<TFrielink> selectBlackFrieLinks();
    List<TFrielink> selectShowingFrieLinks();
    List<TFrielink> selectHideFrieLinks();
    TFrielink selectFrieLinkByDomain(String doamin);
    TFrielink selectFrieLinkByLink(String link);
    int insertFrieLink(TFrielink frielink);
    int updateByFrieLink(TFrielink frieLink);
}