    package com.faijan.crud.StudentDemo.controller;

    import com.faijan.crud.StudentDemo.dto.StudentDto;
    import com.faijan.crud.StudentDemo.dto.StudentRequest;
    import com.faijan.crud.StudentDemo.service.StudentServiceImpl;
    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.responses.ApiResponse;
    import io.swagger.v3.oas.annotations.responses.ApiResponses;
    import jdk.jfr.Description;
    import lombok.extern.slf4j.Slf4j;
    import org.apache.coyote.Response;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @Slf4j
    @RestController
    @RequestMapping("/student")
    public class StudentController {
        private static final String CLASS_NAME = StudentController.class.getName();
        @Autowired
        StudentServiceImpl studentService;

        String methodName;

        @GetMapping("/getAllStudents")
        @Operation(summary = "Get All Students", description = "API documentation for Student Service")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful retrieval of Students") })
        public List<StudentDto>  getStudents(){
            methodName = ".getStudents";
            log.info(CLASS_NAME + methodName + "::ENTER");
            List<StudentDto> studentList = studentService.getStudents();
            log.info(CLASS_NAME + methodName + "::methodName");
            return studentList;
        }

        @GetMapping("/{id}")
        @Operation(summary = "Get Student By Id", description = "API documentation for Student Service")
        @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "User found with id"),
                @ApiResponse(responseCode = "404", description = "User Not Found")})
        public ResponseEntity<StudentDto> getStudentByID(@PathVariable Long id){
            methodName =".getStudentByID";
            log.info(CLASS_NAME + methodName + "::ENTER");
            StudentDto student = studentService.getStudentById(id);
            log.info(CLASS_NAME + methodName + "::EXIT");
            return ResponseEntity.status(HttpStatus.OK).body(student);
        }

        @PostMapping("/addStudent")
        @Operation(summary = "Add Student", description = "API documentation for Student Service")
        @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Student Sucessfully added")})
        public ResponseEntity addStudent(@RequestBody StudentRequest studentRequest) {
            methodName = ".addStudent";
            log.info(CLASS_NAME + methodName + "::Enter");
            log.info(CLASS_NAME + methodName + ":: Add student service call triggered :" + studentRequest);
            StudentDto studentDto = studentService.addStudent(studentRequest);
            log.info(CLASS_NAME + methodName + "::Exit");
            return ResponseEntity.status(HttpStatus.CREATED).body(studentDto);
        }

        @DeleteMapping("/deleteStudent/{id}")
        @Operation(summary = "Delete Student", description = "API documentation for Student Service")
        @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "User Successfully Deleted "),
                @ApiResponse(responseCode = "404", description = " User Not Found with the given Id")})
        public ResponseEntity deleteStudent(@PathVariable Long id){
            methodName = ".deleteStudent";
            log.info(CLASS_NAME + methodName + "::ENTER");
            studentService.deleteStudent(id);
            log.info(CLASS_NAME + methodName + "::EXIT");
            return ResponseEntity.status(HttpStatus.OK).body("Student succesfully deleted");
        }

        @PutMapping("/updateStudent/{id}")
        @Operation(summary = "Update User By Id", description = "API documentation for Student Service")
        @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Student Details are Successfully Updated"),
                @ApiResponse(responseCode = "404", description = "User Not Found with the given Id")})
        public ResponseEntity updateUser(@PathVariable Long id, @RequestBody StudentRequest studentRequest){
            methodName = ".updateStudent";
            log.info(CLASS_NAME + methodName + "::ENTER");
            StudentDto studentDto = studentService.updateStudent(id,studentRequest);
            log.info(CLASS_NAME + methodName + "::EXIT");
            return  ResponseEntity.status(HttpStatus.OK).body(studentDto);
        }

    }
