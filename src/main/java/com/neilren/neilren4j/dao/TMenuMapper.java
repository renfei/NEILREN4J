package com.neilren.neilren4j.dao;

import com.neilren.neilren4j.dbentity.TMenu;
import java.util.List;

public interface TMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TMenu record);

    TMenu selectByPrimaryKey(Long id);

    List<TMenu> selectAll();

    int updateByPrimaryKey(TMenu record);
}