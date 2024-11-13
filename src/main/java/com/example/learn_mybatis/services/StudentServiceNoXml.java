package com.example.learn_mybatis.services;

import com.example.learn_mybatis.entities.Student;

public interface StudentServiceNoXml {
    void create(Student student);
    Student getStudentById(Integer id);
    void update(Student student);
    void delete(Integer id);
}
