package com.neilren.neilren4j.entity;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName AliyunGreenWeb
 * @Description TODO
 * @Date 2018/8/6 22:42
 */
public class AliyunGreenWeb {
    private float rate;
    private String suggestion;
    private String label;
    private String scene;

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public String getSuggestionZh() {
        if (suggestion.equals("pass"))
            return "通过";
        else if (suggestion.equals("review"))
            return "重审";
        else if (suggestion.equals("block"))
            return "命中";
        else return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getLabel() {
        return label;
    }

    public String getLabelZh() {
        if (label.equals("normal"))
            return "正常文本";
        else if (label.equals("spam"))
            return "含垃圾信息";
        else if (label.equals("ad"))
            return "广告";
        else if (label.equals("politics"))
            return "渉政";
        else if (label.equals("terrorism"))
            return "暴恐";
        else if (label.equals("abuse"))
            return "辱骂";
        else if (label.equals("porn"))
            return "色情";
        else if (label.equals("flood"))
            return "灌水";
        else if (label.equals("contraband"))
            return "违禁";
        else if (label.equals("customized"))
            return "自定义";
        else return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }
}
