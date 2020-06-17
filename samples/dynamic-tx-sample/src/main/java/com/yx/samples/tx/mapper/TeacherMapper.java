package com.yx.samples.tx.mapper;

import com.yx.samples.tx.entiry.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther: yx
 * @Date: 2020-06-17 20:05
 * @Description: TeacherMapper
 */
@Mapper
public interface TeacherMapper {
    @Insert("INSERT INTO teacher (name,age) values (#{name},#{age})")
    boolean addTeacher(@Param("name") String name, @Param("age") Integer age);

    @Select("SELECT * FROM teacher")
    List<Teacher> selectTeachers();
}
