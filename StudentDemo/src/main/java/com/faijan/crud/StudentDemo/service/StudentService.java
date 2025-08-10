package com.faijan.crud.StudentDemo.service;

import com.faijan.crud.StudentDemo.dto.StudentDto;
import com.faijan.crud.StudentDemo.dto.StudentRequest;
import com.faijan.crud.StudentDemo.entity.Student;

import java.util.List;

public interface StudentService {
    public List<StudentDto> getStudents();

    public StudentDto getStudentById(Long id);

    public StudentDto addStudent(StudentRequest studentRequest);

    public void deleteStudent(Long id);

    public StudentDto updateStudent(Long id, StudentRequest studentRequest);
}
