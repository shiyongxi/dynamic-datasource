package com.yx.samples.tx.service;


import com.yx.samples.tx.entiry.Teacher;

import java.util.List;


public interface TeacherService {

    boolean addTeacherWithTx(String name, Integer age);

    boolean addTeacherNoTx(String name, Integer age);


    List<Teacher> selectTeachers();

    void selectTeachersInnerStudents();
}
