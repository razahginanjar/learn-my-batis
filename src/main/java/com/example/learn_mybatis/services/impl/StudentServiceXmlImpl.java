package com.example.learn_mybatis.services.impl;

import com.example.learn_mybatis.entities.Student;
import com.example.learn_mybatis.entities.StudentAndCourseInfo;
import com.example.learn_mybatis.repositories.StudentMapperXml;
import com.example.learn_mybatis.services.StudentServiceXml;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceXmlImpl implements StudentServiceXml {
    private final StudentMapperXml studentMapperXml;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "studentXml", key = "#student.SId")
    public void createStudent(Student student) {
        studentMapperXml.create(student);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "studentXml", key = "#student.SId")
    public void updateStudent(Student student) {
        studentMapperXml.update(student);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "studentXml", key = "#id")
    public void deleteStudent(Integer id) {
        studentMapperXml.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "studentXml", key = "#id")
    public Student getStudentById(Long id) {
        return studentMapperXml.findStudentById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentMapperXml.findAll();
    }

    @Override
    public List<Student> getAllStudentsByNameAndSex(String name, String sex) {
        return studentMapperXml.findAllSpecificNameAndGender(name, sex);
    }

    @Override
    public List<StudentAndCourseInfo> getStudentsWhoScoreC1MoreThanC2(Long firstCId, Long secondCId) {
        return studentMapperXml.findWhoScoreIsScoreInCourseOneThanCourseTwo(firstCId, secondCId);
    }
}
