package com.neilren.neilren4j.service;

import com.neilren.neilren4j.common.baseclass.BaseService;
import com.neilren.neilren4j.common.util.IllegalStrFilterUtil;
import com.neilren.neilren4j.dao.TWorstPasswdMapper;
import com.neilren.neilren4j.dbentity.TWorstPasswd;
import com.neilren.neilren4j.entity.SecuseLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName SecurityService
 * @Description TODO
 * @Date 2018/8/1 16:20
 */
@Service
public class SecurityService extends BaseService {
    @Autowired
    private TWorstPasswdMapper worstPasswdMapper;

    public boolean checkRequest(Object[] args) {
        String str = String.valueOf(args);
        if (!IllegalStrFilterUtil.sqlStrFilter(str)) {
            return false;
        } else if (!IllegalStrFilterUtil.isIllegalStr(str)) {
            return false;
        }
        return true;
    }

    /**
     * 弱密码检测
     *
     * @param pwd
     * @return
     */
    public Map<String, Object> checkWoestPassword(String pwd) {
        pwd = pwd.trim();
        int score = 0;
        boolean hit = false;
        Map<String, Object> map = new HashMap<>();
        map.put("Password", pwd);
        SecuseLevel secuseLevel;
        ///////////////////
        score += checkWoestPasswordPluses(pwd);
        score += checkWoestPasswordSubtraction(pwd);
        TWorstPasswd worstPasswd = worstPasswdMapper.selectByPwd(pwd);
        if (worstPasswd != null) {
            score -= 50;
            hit = true;
        }
        ///////////////////
        if (score > 100) {
            score = 100;
        }
        if (score < 0) {
            score = 0;
        }
        if (score >= 90) {
            secuseLevel = SecuseLevel.VERY_SECURE;
        } else if (score >= 80) {
            secuseLevel = SecuseLevel.SECURE;
        } else if (score >= 70) {
            secuseLevel = SecuseLevel.VERY_STRONG;
        } else if (score >= 60) {
            secuseLevel = SecuseLevel.STRONG;
        } else if (score >= 50) {
            secuseLevel = SecuseLevel.AVERAGE;
        } else if (score >= 25) {
            secuseLevel = SecuseLevel.WEAK;
        } else {
            secuseLevel = SecuseLevel.VERY_WEAK;
        }
        map.put("SecuseLevel", secuseLevel);
        map.put("Score", score);
        map.put("HitDatabase", hit);
        return map;
    }

    private int checkWoestPasswordSubtraction(String pwd) {
        int score = 0, i = 0;
        boolean charUp = judgeContainsStrUp(pwd);
        boolean charLow = judgeContainsStrLow(pwd);
        boolean number = isContainNumber(pwd);
        boolean charSpecia = isSpecialChar(pwd);
        if (charUp) {
            i++;
        }
        if (charLow) {
            i++;
        }
        if (number) {
            i++;
        }
        if (charSpecia) {
            i++;
        }
        switch (i) {
            case 1:
                score -= 50;
                break;
            case 2:
                score -= 25;
                break;
            case 3:
                score -= 15;
                break;
        }
        return score;
    }

    private int checkWoestPasswordPluses(String pwd) {
        int score = 0;
        //长度判断
        if (pwd.length() >= 16) {
            score += 60;
        } else if (pwd.length() >= 10) {
            score += 50;
        } else if (pwd.length() >= 8) {
            score += 25;
        } else if (pwd.length() >= 4) {
            score += 10;
        }
        boolean charUp = judgeContainsStrUp(pwd);
        boolean charLow = judgeContainsStrLow(pwd);
        if (charLow && charUp) {
            score += 20;
        } else if (charLow || charUp) {
            score += 10;
        }
        boolean number = isContainNumber(pwd);
        if (number) {
            score += 10;
        }
        boolean charSpecia = isSpecialChar(pwd);
        if (charSpecia) {
            score += 10;
        }
        return score;
    }

    /**
     * 使用正则表达式来判断字符串中是否包含小写字母
     *
     * @param str 待检验的字符串
     * @return 返回是否包含
     * true: 包含字母 ;false 不包含字母
     */
    public boolean judgeContainsStrLow(String str) {
        String regex = ".*[a-z]+.*";
        Matcher m = Pattern.compile(regex).matcher(str);
        return m.matches();
    }

    /**
     * 使用正则表达式来判断字符串中是否包含大写字母
     *
     * @param str 待检验的字符串
     * @return 返回是否包含
     * true: 包含字母 ;false 不包含字母
     */
    public boolean judgeContainsStrUp(String str) {
        String regex = ".*[A-Z]+.*";
        Matcher m = Pattern.compile(regex).matcher(str);
        return m.matches();
    }

    /**
     * 使用正则表达式来判断字符串中是否包含数字
     *
     * @param str 待检验的字符串
     * @return 返回是否包含
     * true: 包含数字 ;false 不包含数字
     */
    public boolean isContainNumber(String str) {
        String regex = ".*[0-9]+.*";
        Matcher m = Pattern.compile(regex).matcher(str);
        return m.matches();
    }

    /**
     * 判断是否含有特殊字符
     *
     * @param str
     * @return true为包含，false为不包含
     */
    public boolean isSpecialChar(String str) {
        String regEx = ".*[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]+.*";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }
}
