package com.ssh.dao.impl;

import com.ssh.dao.StudentDao;
import com.ssh.domain.Clazz;
import com.ssh.domain.Student;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 18/1/15.
 */
@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public int insert(Student student) {
        /*将班级id格式化整型*/
        int cid = Integer.parseInt(student.getClazzId());
        /*1,根据cid获取班级对象*/
        Clazz clazz = hibernateTemplate.get(Clazz.class, cid);
        /*2绑定班级到该学生对象,用于外键的更新*/
        if (clazz != null) {
            student.setClazz(clazz);
        }
        /*3.保存学生对象*/
        Integer key = (Integer) hibernateTemplate.save(student);
        return key; //返回插入成功的主键id
    }

    /*根据用户名和密码查询学生对象*/
    @Override
    public Student selectByNameAndPsw(Student student) {
        /*查询语句*/
        String sql = "from Student where sname= ? and spassword= ? ";
        /*查询语句包含的参数列表*/
        Object[] args = {student.getSname(), student.getSpassword()};
       /*通过查询语句得到学生对象集合列表*/
        List<Student> students= (List<Student>) hibernateTemplate.find(sql,args);
        /*如果能查询到 则返回第一个学生对象*/
        if (students!=null&&students.size()>0){
            return students.get(0);
        }
        return null;
    }

    @Override
    public List<Student> selectAll() {
        return hibernateTemplate.loadAll(Student.class);
    }

    /*进行条件查询的基础方法\
    *@sql    条件语句,不需要select *的,直接从from开始
    * args   条件语句中对应的参数列表
    * return 返回符合条件的集合对象
    * */
    @Override
    public List<Student> select(String sql, Object[] args) {
        return (List<Student>) hibernateTemplate.find(sql,args);
    }
}
