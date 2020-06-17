package com.yx.samples.druid.mybatis.mapper;

import com.yx.samples.druid.mybatis.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther: yx
 * @Date: 2020-06-17 15:43
 * @Description: UserMapper
 */
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user (name,age) values (#{name},#{age})")
    boolean addUser(@Param("name") String name, @Param("age") Integer age);

    @Select("SELECT * FROM user where age > #{age}")
    List<User> selectUsers(@Param("age") Integer age);
}
