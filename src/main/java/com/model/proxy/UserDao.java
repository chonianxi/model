package com.model.proxy;

import com.model.zhujie.Ztt;

public class UserDao {

    @Ztt
    public void save() {
        System.out.println("----已经保存数据!----");
    }
}
