package com.ssh.aop;

/**
 * Created by dllo on 18/1/15.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void insert() {
        System.out.println("insert执行");
    }

    @Override
    public void delete() {
        System.out.println("delete执行");
    }

    @Override
    public void update() {
        System.out.println("update执行");
    }
}
