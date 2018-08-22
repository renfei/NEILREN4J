package com.neilren.neilren4j.entity;

public enum SecuseLevel {
    VERY_SECURE("非常安全"),
    SECURE("安全"),
    VERY_STRONG("非常强"),
    STRONG("强"),
    AVERAGE("一般"),
    WEAK("弱"),
    VERY_WEAK("非常弱");
    private final String name;

    SecuseLevel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
