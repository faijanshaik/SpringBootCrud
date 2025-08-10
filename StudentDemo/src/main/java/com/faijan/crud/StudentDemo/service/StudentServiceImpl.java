package com.faijan.crud.StudentDemo.service;

import com.faijan.crud.StudentDemo.dto.StudentDto;

import com.faijan.crud.StudentDemo.dto.StudentRequest;
import com.faijan.crud.StudentDemo.entity.Student;
import com.faijan.crud.StudentDemo.repository.StudentRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.plaf.metal.MetalTheme;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    public static final String CLASS_NAME = StudentServiceImpl.class.getName();

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    String methodName;

    @Override
    public List<StudentDto> getStudents() {
        methodName = ".getStudent";
        List<StudentDto> studentDtos= null;
        try{
            List<Student> students = students = studentRepository.findAll();
            studentDtos = students.stream()
                    .map(student -> new StudentDto(student.getId(), student.getSName(),student.getSEmail()))
                    .toList();
        } catch(Exception e){
            log.error(CLASS_NAME + methodName + ":: Exception Occured :" + e.getMessage());
        }
        return studentDtos;
    }

    @Override
    public StudentDto getStudentById(Long id) {
        methodName = ".getStudentById";
        log.info(CLASS_NAME + methodName + "::ENTER");
        log.info(CLASS_NAME + methodName + "::  student By Id call triggered");
        Student student=studentRepository.findById(id)
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Student not found with id : {}" +id));
        log.info(CLASS_NAME + methodName + "::EXIT");
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto addStudent(StudentRequest studentRequest) {
        methodName = ".addStudet";
        log.info(CLASS_NAME + methodName + "::ENTER");
        Student student=null;
        try{
            Student newStudent = modelMapper.map(studentRequest, Student.class);
            log.info(CLASS_NAME + methodName + ":: add student call triggered");
             student = studentRepository.save(newStudent);
        } catch (Exception e) {
            log.error(CLASS_NAME + methodName + "::Exception Occured :" +e.getMessage());
        }
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void deleteStudent(Long id) {
        methodName = ".deleteStudent";
        log.info(CLASS_NAME + methodName +"::ENTER");
        if(!studentRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Student not found with id : {}" +id);
        }
        log.info(CLASS_NAME + methodName +"::EXIT");
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, StudentRequest studentRequest) {
        methodName = ".updateStudent";
        log.info(CLASS_NAME + methodName + "::Enter");
        Student updatedStudent=null;
        Student student = modelMapper.map(studentRequest, Student.class);
        try{
            updatedStudent = studentRepository.save(student);
        } catch(Exception e){
            log.error(CLASS_NAME + methodName + "::Exception Occured :" +e.getMessage());
        }
        return modelMapper.map(student, StudentDto.class);
    }
}
