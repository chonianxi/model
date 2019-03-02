package com.model.thread;

import java.io.Serializable;

public class UserTest implements Serializable {
    private static final long serialVersionUID = -7433053777646757513L;
    private String name;
    private int num;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
