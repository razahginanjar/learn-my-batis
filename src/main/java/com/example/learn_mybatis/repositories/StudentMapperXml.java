package com.example.learn_mybatis.repositories;

import com.example.learn_mybatis.entities.Student;
import com.example.learn_mybatis.entities.StudentAndCourseInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapperXml {

    void update(Student student);
    Student findStudentById(Long id);
    void delete(Integer id);
    void create(Student student);
    List<Student> findAll();
    List<Student> findAllSpecificNameAndGender(@Param(value = "name") String name,
                                               @Param(value = "sex") String sex);
    List<StudentAndCourseInfo> findWhoScoreIsScoreInCourseOneThanCourseTwo(
            @Param(value = "firstCId") Long fitsCId,
            @Param(value = "secondCId") Long secondCId
    );
}
