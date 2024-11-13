package com.example.learn_mybatis.services;

import com.example.learn_mybatis.entities.Student;
import com.example.learn_mybatis.entities.StudentAndCourseInfo;

import java.util.List;

public interface StudentServiceXml {
    void createStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Integer id);
    Student getStudentById(Long id);
    List<Student> getAllStudents();
    List<Student> getAllStudentsByNameAndSex(String name, String sex);
    List<StudentAndCourseInfo> getStudentsWhoScoreC1MoreThanC2(Long firstCId, Long secondCId);
}
