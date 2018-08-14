package com.neilren.neilren4j.dao;

import com.neilren.neilren4j.dbentity.TLogAccess;
import java.util.List;

public interface TLogAccessMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TLogAccess record);

    TLogAccess selectByPrimaryKey(Long id);

    List<TLogAccess> selectAll();

    int updateByPrimaryKey(TLogAccess record);
}