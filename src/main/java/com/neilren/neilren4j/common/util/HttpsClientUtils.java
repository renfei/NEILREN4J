package com.neilren.neilren4j.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName HttpsClientUtils
 * @Description TODO
 * @Date 2018/8/6 21:54
 */
@Service
public class HttpsClientUtils {
    public static DefaultHttpClient getNewHttpsClient(HttpClient httpClient) {

        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] arg0,
                                               String arg1) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] arg0,
                                               String arg1) throws CertificateException {
                }
            };
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("https", 443, ssf));
            ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);
            return new DefaultHttpClient(mgr, httpClient.getParams());
        } catch (Exception ex) {
            return null;
        }

    }

    public static DefaultHttpClient getNewHttpClient(HttpClient httpClient) {

        try {
            SchemeRegistry registry = new SchemeRegistry();
            ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);
            return new DefaultHttpClient(mgr, httpClient.getParams());
        } catch (Exception ex) {
            return null;
        }

    }

    public String getPageHtml(String currentUrl) {
        HttpClient httpClient = new DefaultHttpClient();
        if (currentUrl.substring(0, 5).equals("https"))
            httpClient = HttpsClientUtils.getNewHttpsClient(httpClient);
        String html = "";
        HttpGet request = new HttpGet(currentUrl);
        request.setHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity mEntity = response.getEntity();
                html = EntityUtils.toString(mEntity);
            } else return null;
        } catch (IOException e) {
            return "";
        }
        return html.toString();
    }
}
