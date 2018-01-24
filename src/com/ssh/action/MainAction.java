package com.ssh.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.PageBean.PageBean;
import com.ssh.domain.Clazz;
import com.ssh.domain.Student;
import com.ssh.service.ClazzService;
import com.ssh.service.StudentService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 18/1/15.
 */
@Controller("mainAction")
@Scope("prototype")//prototype管创建不管生命周期
public class MainAction extends ActionSupport implements ModelDriven<Student> {
    /*action持有service层对象*/
    @Resource //用Resource装配
    private ClazzService clazzService; //班级服务对象
    @Resource
    private StudentService studentService; //学生服务对象

    /*存放班级列表的集合*/
    private List<Clazz> clazzes;

    /*注册/登录页面提交的数据对象*/
    private Student student;
    private List<Student> studentList;

    /*获取前端所有班级的列表*/
    public String getClazzAll() {
    /*通过service层获取班级列表*/
        clazzes = clazzService.selectAll();

        return SUCCESS;
    }

    /*发起注册请求*/
    public String register() {
        System.out.println(student); //打断点debug
        System.out.println(student.getClazzId());
        //调用注册服务
        studentService.register(student);


        return SUCCESS;
    }

    /*发起登录请求*/
    public String login() {
        /*执行登录*/
        Student stu = studentService.login(student);
        if (stu == null) {
            return LOGIN;
        }

        /*保存登录成功之后学生对象*/
        Map<String, Object> sessions = ActionContext.getContext().getSession();

        sessions.put("student", stu);
        return SUCCESS;
    }

    /*获取所有学生列表集合*/
    public String selectStudentList(){
        System.out.println("页面请求"+student);
        studentList = studentService.select(student);

        return SUCCESS;
    }

    /*获取学生列表 加入分页查询*/
    private  int pageNum=1;//当前页面
    private  int pageSize=3;//每页显示的条数
    public  String findStudent(){
        /*分页查询*/
        PageBean<Student> pageBean=studentService.selectAll(student,pageNum,pageSize);
        /*存储pageBean对象 用于前端页面的显示*/
        Map<String,Object> session=ActionContext.getContext().getSession();
        session.put("pageBean",pageBean);
        return SUCCESS;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Clazz> getClazzes() {
        return clazzes;
    }

    public void setClazzes(List<Clazz> clazzes) {
        this.clazzes = clazzes;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override  //模型驱动?
    public Student getModel() {
        student = new Student();
        return student;
    }
}
