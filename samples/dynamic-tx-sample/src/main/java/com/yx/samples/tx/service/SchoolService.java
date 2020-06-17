package com.yx.samples.tx.service;

public interface SchoolService {

    void selectTeachersAndStudents();

    void selectTeachersInnerStudents();

    void addTeacherAndStudent();

    void addTeacherAndStudentWithTx();
}
