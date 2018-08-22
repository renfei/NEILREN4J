package com.neilren.neilren4j.common.util;

import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName ExecLinuxCMD
 * @Description TODO
 * @Date 2018/8/1 10:45
 */
@Service
public class ExecLinuxCMD {
    public String host(String host) {
        return exec("host " + host).toString();
    }

    public String whois(String host) {
        return exec("whois " + host).toString();
    }

    private static Object exec(String cmd) {
        try {
            String[] cmdA = {"/bin/sh", "-c", cmd};
            Process process = Runtime.getRuntime().exec(cmdA);
            LineNumberReader br = new LineNumberReader(new InputStreamReader(
                    process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
