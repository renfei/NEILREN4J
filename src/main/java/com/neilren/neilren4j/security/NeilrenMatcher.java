package com.neilren.neilren4j.security;

import com.neilren.neilren4j.entity.User;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.stereotype.Component;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName 自定义匹配器
 * @Description TODO
 * @Date 2018/8/4 14:53
 */
@Component
public class NeilrenMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        //获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
        String inPassword = new String(utoken.getPassword());
        //获得数据库中的用户
        User user = (User) info.getCredentials();
        if (user == null) {
            return false;
        }
        //进行密码的比对
        return user.checkSha512Passwd(inPassword);
    }
}
