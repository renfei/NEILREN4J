package com.neilren.neilren4j.dao;

import com.neilren.neilren4j.dbentity.TUserRole;
import java.util.List;

public interface TUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TUserRole record);

    TUserRole selectByPrimaryKey(Long id);

    List<TUserRole> selectAll();

    int updateByPrimaryKey(TUserRole record);
}