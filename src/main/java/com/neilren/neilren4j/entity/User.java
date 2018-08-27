package com.neilren.neilren4j.entity;

import com.neilren.neilren4j.common.util.Encrypt;
import com.neilren.neilren4j.common.util.GoogleAuthenticator;
import com.neilren.neilren4j.dbentity.TUser;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName User
 * @Description TODO
 * @Date 2018/7/30 16:54
 */
@Data
public class User extends TUser {
    @Autowired
    private Encrypt encrypt;
    @Autowired
    private GoogleAuthenticator googleAuthenticator;

    public boolean checkPasswdAndTOTP(String passwd, String totp) {
        return checkPasswd(passwd) && checkTOTP(totp);
    }

    public boolean checkSha512PasswdAndTOTP(String SHA512, String totp) {
        return checkSha512Passwd(SHA512) && checkTOTP(totp);
    }

    public boolean checkPasswd(String passwd) {
        if(encrypt==null){
            encrypt=new Encrypt();
        }
        String result = "";
        result = encrypt.SHA512Paawd(passwd, this.getSalt());
        return result.equals(this.getPasswd());
    }

    public boolean checkSha512Passwd(String SHA512) {
        if(encrypt==null){
            encrypt=new Encrypt();
        }
        String result = "";
        result = encrypt.SHA512Salt(SHA512, this.getSalt());
        return result.equals(this.getPasswd());
    }

    public boolean checkTOTP(String totp) {
        String secret = this.getSecret();
        if (secret == null || "".equals(secret)) {
            return true;
        } else {
            return googleAuthenticator.authcode(totp, secret);
        }
    }
}
