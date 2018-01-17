package com.ssh.aop;

import org.junit.Test;

/**
 * Created by dllo on 18/1/15.
 * 切面编程:与当前业务不相关的业务
 * 而动态代理:只有执行到才会生成代理(通过反射判断)
 */

/*静态代理*/
public class MainTest {

    @Test
    public  void test(){
        UserDao userDao =new UserDaoImpl();
        userDao.insert();
        userDao.delete();
        userDao.update();
    }
    /*静态代理:需要增加一个中间类*/
    @Test
    public void testStaticProxy(){

        //new出三个对象作为UserDaoProxy方法的参数
        UserDao userDao =new UserDaoImpl();
        MyTransactionManager mym =new MyTransactionManager();
        MyLog log =new MyLog();
        /*定义代理对象*/
        UserDaoProxy proxy =new UserDaoProxy(mym,log,userDao);
       /*通过静态代理对象调用接口中的方法*/
        proxy.insert();
        proxy.update();
        proxy.delete();
    }
    /*
    * jdk动态代理 (被代理的类必须要实现接口)
    * 1,实现InvocationHandler接口进行方法拦截
    * 2,通过Proxy生成动态代理对象
    *
    * */
    @Test
    public  void  restProxy(){
        MyTransactionManager mym =new MyTransactionManager(); //切面
        MyLog log =new MyLog();

        UserDao userDao =new UserDaoImpl(); //业务
        /*公共代理对象*/
        CommonProxy proxy =new CommonProxy(mym,log);
        /*通过公共代理类得到动态生成的代理接口类对象*/
        UserDao userDaoProxy= (UserDao) proxy.getProxyObject(userDao);
        /*代理类调用接口方法*/
        userDaoProxy.insert();
        userDaoProxy.delete();
        userDaoProxy.update();
    }
}
