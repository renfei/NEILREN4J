package com.neilren.neilren4j.common.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName DomainUtils
 * @Description TODO
 * @Date 2018/8/6 21:13
 */
@Component
public class DomainUtils {
    String domainRules = "^(com.cn|net.cn|org.cn|gov.cn|com.hk|com|net|org|int|edu|gov|mil|arpa|asia|biz|info|name|pro|coop|aero|museum|ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cc|cf|cg|ch|ci|ck|cl|cm|cn|co|cq|cr|cu|cv|cx|cy|cz|de|dj|dk|dm|do|dz|ec|ee|eg|eh|es|et|ev|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gh|gi|gl|gm|gn|gp|gr|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|in|io|iq|ir|is|it|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|ml|mm|mn|mo|mp|mq|mr|ms|mt|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nt|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|pt|pw|py|qa|re|ro|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|sl|sm|sn|so|sr|st|su|sy|sz|tc|td|tf|tg|th|tj|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|us|uy|va|vc|ve|vg|vn|vu|wf|ws|ye|yu|za|zm|zr|zw)$";

    /**
     * 域名简单拆分，不进行任何逻辑处理
     *
     * @param domain 域名
     * @return 未经过验证的拆分结果
     */
    public String[] splitDomain(String domain) {
        String[] arrDomain = domain.split("\\.");
        return arrDomain;
    }

    /**
     * 验证域名合法性并进行域名拆分
     *
     * @param domain 域名
     * @return 域名拆分结果，非法域名返回 Null
     */
    public String[] splitDomainVerification(String domain) {
        //先转换成小写字母
        domain = domain.toLowerCase();
        if (domainVerification(domain)) {
            //对 com.cn 这样的二级顶级域名进行修正
            String[] arrDomain = splitDomain(domain);
            //检测顶级域名是否存在
            if (Pattern.compile(domainRules).matcher(arrDomain[arrDomain.length - 2] + "." + arrDomain[arrDomain.length - 1]).find()) {
                String[] arrStr = new String[arrDomain.length - 1];
                for (int i = 0; i < arrDomain.length; i++) {
                    //如果到了最后两个，对最后两个进行合并
                    if (i == arrDomain.length - 2) {
                        arrStr[i] = arrDomain[i] + "." + arrDomain[i + 1];
                        break;
                    }
                    arrStr[i] = arrDomain[i];
                }
                return arrStr;
            } else {
                return splitDomain(domain);
            }
        } else {
            return null;
        }
    }

    /**
     * 域名合法性检测
     *
     * @param domain
     * @return
     */
    public boolean domainVerification(String domain) {
        //先转换成小写字母
        domain = domain.toLowerCase();
        //域名不能以 . 开头 或 . 结尾
        if (domain.toCharArray()[0] == '.' || domain.toCharArray()[0] == '-')
            return false;
        if (domain.toCharArray()[domain.length() - 1] == '.')
            return false;
        String regEx = "^[a-z,0-9,\\-,.]*$";
        //检测域名组成因子，必须只能是字母、数字、连词符
        if (Pattern.compile(regEx).matcher(domain).find()) {
            String[] arrDomain = splitDomain(domain);
            //检测域名长度，如果只有一级域名则不合法
            if (arrDomain.length < 1) {
                return false;
            }
            //检测顶级域名是否存在
            if (Pattern.compile(domainRules).matcher(arrDomain[arrDomain.length - 1]).find()) {
                return true;
            } else if (Pattern.compile(domainRules).matcher(arrDomain[arrDomain.length - 2] + "." + arrDomain[arrDomain.length - 1]).find()) {
                //一级域名不存在，可能是二级 类似 com.cn ，再次进行检测
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 查询域名的Whois信息
     *
     * @param domain 域名
     * @return whois信息
     */
    public String queryWhois(String domain) {
        String[] arrDomain = splitDomainVerification(domain);
        if (arrDomain != null && arrDomain.length > 1) {
            try {
                String server = "";
                int DEFAULT_PORT = 43;
                String tld = arrDomain[arrDomain.length - 1];
                if ("com".equals(tld)) {
                    server = "whois.verisign-grs.com";
                } else if ("net".equals(tld)) {
                    server = "whois.verisign-grs.com";
                } else if ("org".equals(tld)) {
                    server = "whois.pir.org";
                } else if ("cn".equals(tld) || "com.cn".equals(tld) || "net.cn".equals(tld) || "org.cn".equals(tld)) {
                    server = "whois.cnnic.cn";
                } else if ("jp".equals(tld)) {
                    server = "whois.jprs.jp";
                } else if ("kr".equals(tld)) {
                    server = "whois.kr";
                }
                if (server == "")
                    return null;
                Socket socket = new Socket(server, DEFAULT_PORT);
                String lineSeparator = "\r\n";

                PrintWriter out = new PrintWriter(socket.getOutputStream());
                out.println(arrDomain[arrDomain.length - 2] + "." + arrDomain[arrDomain.length - 1]);
                out.flush();

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                StringBuilder ret = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    ret.append(line + lineSeparator);
                }
                socket.close();
                return ret.toString();
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 获取域名的注册时间
     *
     * @param domain 域名
     * @return 注册时间
     */
    public Date getRegisterDate(String domain) {
        String whoisInfo = queryWhois(domain);
        if (whoisInfo != null) {
            String[] arrWhoisInfo = whoisInfo.split("\r");
            String[] arrDomain = splitDomain(domain);
            //CN域名的whois信息不同，单独处理
            if (arrDomain[arrDomain.length - 1].toLowerCase().equals("cn")) {
                for (String info : arrWhoisInfo) {
                    if (info.indexOf("Registration Time") != -1) {
                        info = info.trim();
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        df.setTimeZone(TimeZone.getTimeZone("UTC"));
                        String strRegisterDate = info.substring("Registration Time: ".length(), info.length());
                        try {
                            return df.parse(strRegisterDate);
                        } catch (Exception e) {
                            return null;
                        }
                    }
                }
            } else {
                for (String info : arrWhoisInfo) {
                    if (info.indexOf("Creation Date") != -1) {
                        info = info.trim();
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                        df.setTimeZone(TimeZone.getTimeZone("UTC"));
                        String strRegisterDate = info.substring("Creation Date: ".length(), info.length());
                        try {
                            return df.parse(strRegisterDate);
                        } catch (Exception e) {
                            return null;
                        }
                    }
                }
            }
            return null;
        } else
            return null;
    }
}
