package com.neilren.neilren4j.dao;

import com.neilren.neilren4j.dbentity.TUser;

import java.util.List;

public interface TUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TUser record);

    TUser selectByPrimaryKey(Long id);

    TUser selectByUserNameOrEmail(String username);

    List<TUser> selectAll();

    int updateByPrimaryKey(TUser record);
}