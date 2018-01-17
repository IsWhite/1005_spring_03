package com.ssh.dao;

import com.ssh.domain.Student;

import java.util.List;

/**
 * Created by dllo on 18/1/15.
 */
public interface StudentDao {

    int insert(Student student);

    Student selectByNameAndPsw(Student student);

    List<Student> selectAll();

    List<Student> select(String sql,Object[] args);
}
