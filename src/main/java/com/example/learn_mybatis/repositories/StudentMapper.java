package com.example.learn_mybatis.repositories;

import com.example.learn_mybatis.entities.Student;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StudentMapper {
    @Insert(value = "insert into student(s_id, s_name, s_sex, s_brith)\n" +
            "            values( #{sId}, #{sName}, #{sSex}, #{sBirth})")
    void createStudent(Student student);

    @Update(value = "update student\n" +
            "        set s_name = #{sName}, s_sex = #{sSex}, s_brith = #{sBirth}\n" +
            "        where s_id = #{sId}")
    void updateStudent(Student student);

    @Select(value = "SELECT * FROM student as S WHERE S.s_Id = #{id}")
    @Results({
            @Result(property = "sId", column = "s_Id"),
            @Result(property = "sName", column = "s_name"),
            @Result(property = "sSex", column = "s_sex"),
            @Result(property = "sBirth", column = "s_brith")
    })
    Student getStudentById(Integer id);

    @Delete(value = "delete from student\n" +
            "         where s_id = #{id}")
    void deleteById(Integer id);
}
