package com.yx.samples.tx.service.impl;

import com.yx.dynamic.datasource.annotation.DS;
import com.yx.samples.tx.entiry.Teacher;
import com.yx.samples.tx.mapper.TeacherMapper;
import com.yx.samples.tx.service.StudentService;
import com.yx.samples.tx.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@DS("teacher")
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;
    @Autowired
    private StudentService studentService;

    @Override
    @Transactional
    public boolean addTeacherWithTx(String name, Integer age) {
        return teacherMapper.addTeacher(name, age);
    }

    @Override
    public boolean addTeacherNoTx(String name, Integer age) {
        return teacherMapper.addTeacher(name, age);
    }

    @Override
    public List<Teacher> selectTeachers() {
        return teacherMapper.selectTeachers();
    }

    @Override
    public void selectTeachersInnerStudents() {
        teacherMapper.selectTeachers();
        studentService.selectStudents();
    }
}
