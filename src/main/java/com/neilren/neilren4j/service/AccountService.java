package com.neilren.neilren4j.service;

import com.alibaba.fastjson.JSON;
import com.neilren.neilren4j.common.baseclass.BasePageService;
import com.neilren.neilren4j.dao.TRoleMapper;
import com.neilren.neilren4j.dao.TUserMapper;
import com.neilren.neilren4j.dbentity.TRole;
import com.neilren.neilren4j.dbentity.TUser;
import com.neilren.neilren4j.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName AccountService
 * @Description TODO
 * @Date 2018/8/4 14:57
 */
@Service
public class AccountService extends BasePageService {
    @Autowired
    private TUserMapper userMapper;
    @Autowired
    private TRoleMapper roleMapper;

    public User findUserByUserName(String userName) {
        TUser tUser = userMapper.selectByUserNameOrEmail(userName);
        if (tUser == null) {
            return null;
        }
        String json = JSON.toJSONString(tUser);
        return JSON.parseObject(json, User.class);
    }

    public List<TRole> getRoleByUser(Long userid) {
        return roleMapper.gerRoleByUserId(userid);
    }
}
