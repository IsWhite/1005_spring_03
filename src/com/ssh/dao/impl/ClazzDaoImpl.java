package com.ssh.dao.impl;

import com.ssh.dao.ClazzDao;
import com.ssh.domain.Clazz;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 18/1/15.
 */
@Repository("clazzDao")
public class ClazzDaoImpl implements ClazzDao {

    /* 定义一个HibernateTemplate对象,里面封装类一系列
    hibernate访问数据库的方法*/
    @Resource
    private HibernateTemplate hibernateTemplate; //需要在spring声明,引入hibernateTemplate对象

    @Override
    public List<Clazz> selectAll() {
        return hibernateTemplate.loadAll(Clazz.class);
    }
}
