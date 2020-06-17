package com.yx.samples.tx.service;


import com.yx.samples.tx.entiry.Student;

import java.util.List;

public interface StudentService {

    boolean addStudentWithTx(String name, Integer age);

    boolean addStudentNoTx(String name, Integer age);


    List<Student> selectStudents();
}
