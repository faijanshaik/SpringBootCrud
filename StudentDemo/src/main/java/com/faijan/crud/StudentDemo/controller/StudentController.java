package com.faijan.crud.StudentDemo.controller;

import com.faijan.crud.StudentDemo.dto.StudentDto;
import com.faijan.crud.StudentDemo.dto.StudentRequest;
import com.faijan.crud.StudentDemo.service.StudentService;
import com.faijan.crud.StudentDemo.service.StudentServiceImpl;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {
    private static final String CLASS_NAME = StudentController.class.getName();
    @Autowired
    StudentServiceImpl studentService;

    String methodName;

    @GetMapping("/getAllStudents")
    public List<StudentDto>  getStudents(){
        methodName = ".getStudents";
        log.info(CLASS_NAME + methodName + "::ENTER");
        List<StudentDto> studentList = studentService.getStudents();
        log.info(CLASS_NAME + methodName + "::methodName");
        return studentList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentByID(@PathVariable Long id){
        methodName =".getStudentByID";
        log.info(CLASS_NAME + methodName + "::ENTER");
        StudentDto student = studentService.getStudentById(id);
        log.info(CLASS_NAME + methodName + "::EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @PostMapping("/addStudent")
    public ResponseEntity addStudent(@RequestBody StudentRequest studentRequest) {
        methodName = ".addStudent";
        log.info(CLASS_NAME + methodName + "::Enter");
        log.info(CLASS_NAME + methodName + ":: Add student service call triggered :" + studentRequest);
        StudentDto studentDto = studentService.addStudent(studentRequest);
        log.info(CLASS_NAME + methodName + "::Exit");
        return ResponseEntity.status(HttpStatus.CREATED).body(studentDto);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id){
        methodName = ".deleteStudent";
        log.info(CLASS_NAME + methodName + "::ENTER");
        studentService.deleteStudent(id);
        log.info(CLASS_NAME + methodName + "::EXIT");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateStudent/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody StudentRequest studentRequest){
        methodName = ".updateStudent";
        log.info(CLASS_NAME + methodName + "::ENTER");
        studentService.updateStudent(id,studentRequest);
    }

}
