package com.yx.samples.tx.mapper;

import com.yx.samples.tx.entiry.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther: yx
 * @Date: 2020-06-17 20:05
 * @Description: StudentMapper
 */
@Mapper
public interface StudentMapper {
    @Insert("INSERT INTO student (name,age) values (#{name},#{age})")
    boolean addStudent(@Param("name") String name, @Param("age") Integer age);

    @Select("SELECT * FROM student")
    List<Student> selectStudents();
}
