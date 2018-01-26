package com.ssh.service.impl;

import com.ssh.PageBean.PageBean;
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
        Object[] params = null;//存放条件语句中的参数列表
        if (student != null) {
            List<Object> args = new ArrayList<>();//Object泛型,找不到arrayList,但写上不报错
           /*进行条件查询*/
           /*名字不为空 sql语句拼接姓名*/
            if (!StringUtils.isBlank(student.getSname())) {
                sql += "and sname like ?";
                args.add("%" + student.getSname() + "%");
            }
           /*班级id不为空 sql语句拼接班级id的外键*/
            if (!StringUtils.isBlank(student.getClazzId())) {
                sql += "and clazzId = ? ";
                /*班级主键id需要转换为整型*/
                int cid = Integer.parseInt(student.getClazzId());
                args.add(cid);
            }
            /*将动态集合转换为数组*/
            params = args.toArray();

        }
        /*调用dao层的条件查询*/
        return studentDao.select(sql, params);
    }

    /**
     * 带条件的分页查询
     *
     * @param student  包含了参数列表的学生对象
     * @param pageNum  查询的是第几页
     * @param pageSize 每页显示的条数
     * @return 返回一个包含数译分页信息的pageBean对象
     */
    @Override
    public PageBean<Student> selectAll(Student student, int pageNum, int pageSize) {
        /*条件查询*/

        /*拼接参数*/
        String condition = "";//条件语句
        Object[] params = null;//参数列表
        List<Object> args = new ArrayList<>();//参数列表集合,用于最后转换参数数组的

        /*拼接名字*/
        if (!StringUtils.isBlank(student.getSname())) {

            condition += "and sname like ?";
            args.add("%" + student.getSname() + "%");
        }
        /*拼接班级*/
        if (!StringUtils.isBlank(student.getClazzId())) {
            condition += "and clazzId = ?";
            int cid=Integer.parseInt(student.getClazzId());
            args.add(cid);
        }
        /*将参数列表集合转换为数组*/
        params=args.toArray();


        /*分页查询*/
        int totalRecord =studentDao.getTotalRecord(condition,params);//获取符合条件的总条数
        /*创建分页对象*/
        PageBean<Student> pageBean=new PageBean<>(pageNum,pageSize,totalRecord);
        /*查询符合条件的学生集合*/
        List<Student> students =studentDao.selectAll(
                condition,params,
                pageBean.getStartIndex(),
                pageBean.getPageSize());

        /*将集合设置到pageBean这个对象中*/
        pageBean.setBeanList(students);

        return pageBean;//将分页对象返回
    }
}
