package com.ssh.aop;

/**
 * Created by dllo on 18/1/15.
 */
/*静态代理,中间类,依然先继承接口,使用构造方法
* */
public class UserDaoProxy implements UserDao {
    /*要插入切面对象,与业务无关的事务,日志对象*/
    private MyTransactionManager mym;
    private MyLog myLog;

    /*要求的目标对象*/
    private  UserDao userDao;

    /*!!使用构造方法给成员变量赋值*/
    public UserDaoProxy(MyTransactionManager mym, MyLog myLog, UserDao userDao) {
        this.mym = mym;
        this.myLog = myLog;
        this.userDao = userDao;
    }

    @Override
    public void insert() {
        mym.begin();
        myLog.start();
        //调用真正的业务方法
        userDao.insert();
        myLog.end();
        mym.commit();

    }

    @Override
    public void delete() {
        mym.begin();
        myLog.start();
        //调用真正的业务方法
        userDao.delete();
        myLog.end();
        mym.commit();
    }

    @Override
    public void update() {
        mym.begin();
        myLog.start();
        //调用真正的业务方法
        userDao.update();
        myLog.end();
        mym.commit();
    }
}
