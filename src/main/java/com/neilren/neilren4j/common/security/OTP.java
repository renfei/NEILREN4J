package com.neilren.neilren4j.common.security;

import com.neilren.neilren4j.common.config.Global;
import com.yubico.client.v2.VerificationResponse;
import com.yubico.client.v2.YubicoClient;
import com.yubico.client.v2.exceptions.YubicoValidationFailure;
import com.yubico.client.v2.exceptions.YubicoVerificationException;

/**
 * OTP一次性密码验证，YubiKey提供服务
 *
 * @author NeilRen
 * @version 1.0
 */
public class OTP {

    private YubicoClient yubicoClient = YubicoClient.getClient(Global.YubicoClientId, Global.YubicoClientSecretKey);

    /**
     * 验证OTP正确性
     *
     * @param otp
     * @return
     * @throws YubicoVerificationException
     * @throws YubicoValidationFailure
     */
    public boolean verifyYubicoOTP(String otp) throws YubicoVerificationException, YubicoValidationFailure {
        VerificationResponse response = yubicoClient.verify(otp);
        return response.isOk();
    }

    /**
     * 获取第三方用户ID
     *
     * @param otp 一次性密码
     * @return 第三方用户ID
     */
    public String getYubicoPublicId(String otp) {
        return yubicoClient.getPublicId(otp);
    }
}
