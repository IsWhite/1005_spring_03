package com.ssh.aop;

/**
 * 事务
 */
public class MyTransactionManager {

    public void begin(){
        System.out.println("开启事务");
    }
    public void commit(){
        System.out.println("提交事务");
    }
}
