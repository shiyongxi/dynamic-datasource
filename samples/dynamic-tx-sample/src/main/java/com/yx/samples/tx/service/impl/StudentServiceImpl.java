package com.yx.samples.tx.service.impl;

import com.yx.dynamic.datasource.annotation.DS;
import com.yx.samples.tx.entiry.Student;
import com.yx.samples.tx.mapper.StudentMapper;
import com.yx.samples.tx.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@DS("student")
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    @Transactional
    public boolean addStudentWithTx(String name, Integer age) {
        return studentMapper.addStudent(name, age);
    }

    @Override
    public boolean addStudentNoTx(String name, Integer age) {
        return studentMapper.addStudent(name, age);
    }

    @Override
    public List<Student> selectStudents() {
        return studentMapper.selectStudents();
    }
}
