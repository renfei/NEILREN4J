package com.neilren.neilren4j.dao;

import com.neilren.neilren4j.dbentity.TIp;
import java.util.List;

public interface TIpMapper {
    int insert(TIp record);

    List<TIp> selectAll();

    TIp selectByIP(String ip);
}