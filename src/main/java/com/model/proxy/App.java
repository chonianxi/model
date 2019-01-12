package com.model.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {


    public static void main(String[] args) throws Exception {


        SpringApplication.run(App.class, args);


        //目标对象
        UserDao target = new UserDao();

        System.out.println(System.currentTimeMillis());
        //代理对象
        UserDao proxy = (UserDao)new ProxyFactory(target).getProxyInstance();
        System.out.println(System.currentTimeMillis());
        //执行代理对象的方法
        proxy.save();
        System.out.println(System.currentTimeMillis());
    }

}
