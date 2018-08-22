package com.neilren.neilren4j.common.conf;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName Aliyun
 * @Description TODO
 * @Date 2018/7/31 15:19
 */
public class Aliyun {
    private String accesskey;
    private String secret;
    private OpenSearch openSearch;
    private OSS oss;
    private CDN cdn;

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public OpenSearch getOpenSearch() {
        return openSearch;
    }

    public void setOpenSearch(OpenSearch openSearch) {
        this.openSearch = openSearch;
    }

    public OSS getOss() {
        return oss;
    }

    public void setOss(OSS oss) {
        this.oss = oss;
    }

    public CDN getCdn() {
        return cdn;
    }

    public void setCdn(CDN cdn) {
        this.cdn = cdn;
    }
}
