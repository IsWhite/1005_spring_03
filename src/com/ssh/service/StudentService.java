package com.ssh.service;

import com.ssh.domain.Student;

import java.util.List;

/**
 * Created by dllo on 18/1/15.
 */
public interface StudentService {
    int register(Student student);

    Student login(Student student);

    List<Student> selectAll();
}