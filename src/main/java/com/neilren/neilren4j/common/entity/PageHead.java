package com.neilren.neilren4j.common.entity;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName PageHead
 * @Description TODO
 * @Date 2018/7/16 17:07
 */
@Component
public class PageHead {

    private List<String> css;
    private List<String> jss;
    private String script;

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public List<String> getJss() {
        return jss;
    }

    public void setJss(List<String> jss) {
        this.jss = jss;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
