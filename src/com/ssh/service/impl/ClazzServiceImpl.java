package com.ssh.service.impl;

import com.ssh.dao.ClazzDao;
import com.ssh.domain.Clazz;
import com.ssh.service.ClazzService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 18/1/15.
 */
@Service("clazzService")
public class ClazzServiceImpl implements ClazzService {

    /*定义dao层的接口对象*/
    @Resource    //按照成员变量名字去找
    private ClazzDao clazzDao;
    @Override
    public List<Clazz> selectAll() {
        return clazzDao.selectAll();
    }
}
