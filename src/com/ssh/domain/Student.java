package com.ssh.domain;

/**
 * Created by dllo on 18/1/15.
 */
public class Student {

    private  int sid;
    private String sname;
    private String spassword;
    //保持一个外键 学生所在的班级
    private Clazz clazz;   //通过学生能找到对应的班级
    /*用于接收注册页面中提交的班级id,不需要数据库映射*/
    private String clazzId;

    public Student() {
    }

    public Student(String sname, String spassword) {
        this.sname = sname;
        this.spassword = spassword;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", spassword='" + spassword + '\'' +
                '}';
    }

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
        this.clazzId = clazzId;
    }
}
