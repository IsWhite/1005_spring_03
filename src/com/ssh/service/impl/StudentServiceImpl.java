package com.ssh.service.impl;

import com.ssh.dao.StudentDao;
import com.ssh.domain.Student;
import com.ssh.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 18/1/15.
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentDao studentDao;

    @Override
    public int register(Student student) {
        /*插入一个学生对象*/
        return studentDao.insert(student);
    }

    /*登录方法实现
    *   @student 包含用户名,密码的学生对象
    *   @return  根据用户名,密码查询到学生对象,如果查找失败返回null
    * */
    @Override
    public Student login(Student student) {
        return studentDao.selectByNameAndPsw(student);
    }

    @Override
    public List<Student> selectAll() {
        return studentDao.selectAll();
    }
}
