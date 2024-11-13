package com.example.learn_mybatis.services.impl;

import com.example.learn_mybatis.entities.Student;
import com.example.learn_mybatis.repositories.StudentMapper;
import com.example.learn_mybatis.services.StudentServiceNoXml;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentServiceNoXmlImpl implements StudentServiceNoXml {
    private final StudentMapper studentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "studentNoXml", key = "#student.SId")
    public void create(Student student) {
        studentMapper.createStudent(student);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "studentNoXml", key = "#id")
    public Student getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "studentNoXml", key = "#student.SId")
    public void update(Student student) {
        studentMapper.updateStudent(student);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "studentNoXml", key = "#id")
    public void delete(Integer id) {
        studentMapper.deleteById(id);
    }
}
