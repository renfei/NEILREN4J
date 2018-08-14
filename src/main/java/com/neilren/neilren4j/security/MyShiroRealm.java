package com.neilren.neilren4j.security;

import com.neilren.neilren4j.dbentity.TRole;
import com.neilren.neilren4j.entity.User;
import com.neilren.neilren4j.service.AccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName MyShiroRealm
 * @Description TODO
 * @Date 2018/8/4 11:41
 */
@Component
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private AccountService accountService;

    /**
     * 权限认定
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        if (user == null) {
            return null;
        }
        List<TRole> roles = accountService.getRoleByUser(user.getId());
        for (TRole role : roles) {
            authorizationInfo.addRole(role.getRoleName());
        }
        return authorizationInfo;
    }

    /**
     * 身份认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken utoken = (UsernamePasswordToken) authenticationToken;//获取用户输入的token
//        String username = utoken.getUsername();
        String username = (String) utoken.getPrincipal();
        User user = accountService.findUserByUserName(username);
        if (user == null) {
            throw new UnknownAccountException("Username:" + username);
        }
        if (user.getUserState() < 0) {
            throw new LockedAccountException("账号被锁定");
        }
        ByteSource byteSource = ByteSource.Util.bytes(user.getSalt());
        return new SimpleAuthenticationInfo(user, user, byteSource, getName());//放入shiro.调用CredentialsMatcher检验密码
    }
}
