package com.ssh.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**动态代理:实现InvocationHandler接口
 *
 * 动态代理的类,可以代理多个接口
 * InvocationHandler lang包下,是一个拦截器,被代理的接口类中的方法
 * 执行时都会进入invoke方法,即监听被代理的所有方法
 */
public class CommonProxy implements InvocationHandler {

    /*要切入的事务*/
    private MyTransactionManager mym;
    private MyLog log;
    /*目标对象*/
    private Object target;

    /*构造方法中只需要定义要切入的事务对象*/
    public CommonProxy(MyTransactionManager mym, MyLog log) {
        this.mym = mym;
        this.log = log;
    }

    /*将一个原始的业务对象srcObj通过Proxy类动态生成一个代理对象,并作为方法返回值返回*/
    public Object getProxyObject(Object srcObj) {
        target = srcObj;//目标对象赋值

        return Proxy.newProxyInstance(                  //译 新的代理实例
                /*第一个参数,被代理类的类加载器*/
                target.getClass().getClassLoader()
                /*第二个参数,被代理类的类接口数组*/
                , target.getClass().getInterfaces()
                /*第三个参数,被代理类中方法监听的拦截器*/
                , this);

    }

    /**
     * 拦截器中的拦截方法,监听代理类中的所有方法执行
     * 自带三个参数
     * @param proxy  通过proxy代理生成的动态代理对象  译 代理人
     * @param method 当前执行的方法                 译 方法
     * @param args   当前执行方法的参数列表*         译 参数列表
     */

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /*获得执行的方法名*/
        String methodName = method.getName();
        /*在insert和delete方法中加入事务和日志*/
        if (methodName.equals("insert") || methodName.equals("delete")) {
            mym.begin();
            log.start();
        /*执行方法,第一个参数对应目标类*/
            method.invoke(target, args);  //invoke放行  译 调用
            mym.commit();
            log.end();
        }else {
            method.invoke(target,args);
        }

        return null;
    }
}
