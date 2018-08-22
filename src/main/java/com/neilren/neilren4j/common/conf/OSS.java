package com.neilren.neilren4j.common.conf;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName OSS
 * @Description TODO
 * @Date 2018/7/31 15:20
 */
public class OSS {
    private String endpoint;
    private String bucketname;
    private String rootPath;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getBucketname() {
        return bucketname;
    }

    public void setBucketname(String bucketname) {
        this.bucketname = bucketname;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }
}
