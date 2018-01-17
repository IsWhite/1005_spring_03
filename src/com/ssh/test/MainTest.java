package com.ssh.test;

import com.ssh.PageBean.PageBean;
import com.ssh.dao.StudentDao;
import com.ssh.domain.Student;
import com.ssh.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 单元测试
 */
public class MainTest {

    private ApplicationContext context;

    @Before
    public  void  init(){
        context = new ClassPathXmlApplicationContext("classpath*:spring-servlet.xml");
    }
    @Test
    public void  testDao(){
        StudentDao studentDao =context.getBean(StudentDao.class);

        String sql ="and sname like ?";
        Object[] params={"%美%"};
        /*获取总条数*/
        int count =studentDao.getTotalRecord(sql,params);
        System.out.println("总条数"+count);
        //分页查询
    List<Student> students =studentDao.selectAll(sql,params,0,3);
        System.out.println(students);
    }

    @Test
    public  void  testService(){
        StudentService studentService=context.getBean(StudentService.class);
        /*参数*/
        Student student =new Student();
        int pageNum=2;
        int pageSize=2;

     PageBean<Student> pageBean = studentService.selectAll(student,pageNum,pageSize);
        System.out.println(pageBean);
    }
}
