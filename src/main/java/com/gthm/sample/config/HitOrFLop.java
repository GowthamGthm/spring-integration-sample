package com.gthm.sample.config;


public enum HitOrFLop {

    HIT("HIT"),
    FLOP("FLOP");

    private final String value;

    HitOrFLop(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    @Override
    public String toString() {
        return "HitOrFLop{" + "value='" + value + '\'' + '}';
    }
}