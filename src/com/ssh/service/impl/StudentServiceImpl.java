package com.ssh.service.impl;

import com.ssh.dao.StudentDao;
import com.ssh.domain.Student;
import com.ssh.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    /*根据条件进行查询，其中参数封装在student类中
    * student包含查询所有参数的对象
    * */
    @Override
    public List<Student> select(Student student) {
        String sql = "from Student where 1=1";
        Object[] params =null;//存放条件语句中的参数列表
        if (student != null) {
            List<Object> args =new ArrayList<>();//Object泛型,找不到arrayList,但写上不报错
           /*进行条件查询*/
           /*名字不为空 sql语句拼接姓名*/
            if (!StringUtils.isBlank(student.getSname())) {
                sql += "and sname like ?";
                args.add("%"+student.getSname()+"%");
            }
           /*班级id不为空 sql语句拼接班级id的外键*/
            if (!StringUtils.isBlank(student.getClazzId())) {
                sql += "and clazzId = ? ";
                /*班级主键id需要转换为整型*/
                int cid =Integer.parseInt(student.getClazzId());
                args.add(cid);
            }
            /*将动态集合转换为数组*/
           params= args.toArray();

        }
        /*调用dao层的条件查询*/
        return studentDao.select(sql,params);
    }
}
