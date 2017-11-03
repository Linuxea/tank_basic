package com.basictank;

/**
 * Created by Linuxea on 11/3/17.
 */

public enum Direction {
    L(0, "left"), R(1, "right"), D(2, "down"), U(3, "up"), LU(4, "left up"), LD(5, "left down"), RU(6, "right up"), RD(7, "right down"), STOP(8, "stop");

    int code;
    String desc;

    Direction(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
