package com.neilren.neilren4j.dao;

import com.neilren.neilren4j.dbentity.TRole;

import java.util.List;

public interface TRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TRole record);

    TRole selectByPrimaryKey(Long id);

    List<TRole> selectAll();

    int updateByPrimaryKey(TRole record);

    public List<TRole> gerRoleByUserId(Long id);
}