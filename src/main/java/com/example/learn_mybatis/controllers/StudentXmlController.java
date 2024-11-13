package com.example.learn_mybatis.controllers;

import com.example.learn_mybatis.entities.Student;
import com.example.learn_mybatis.entities.StudentAndCourseInfo;
import com.example.learn_mybatis.services.StudentServiceXml;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/xml/student")
@Api(tags = "Student Management XML version", value = "Student Controller With XML" )
@RequiredArgsConstructor
public class StudentXmlController {
    private final StudentServiceXml studentMapper;

    @ApiOperation(value = "create student with xml", notes = "for better operation please do not provide null attribute")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 400, message = "Bad Input")
                    ,@ApiResponse(code = 500, message = "Error from the server"),
                    @ApiResponse(code = 200, message = "successfully inserted data")

            })
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> create(@RequestBody Student student) {
        if (student.getSName() == null || student.getSName().isEmpty()) {
            return ResponseEntity.badRequest().body("Student name is required.");
        }
        studentMapper.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                "created"
        );
    }

    @ApiOperation(value = "update student with xml")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 400, message = "Bad Input")
                    ,@ApiResponse(code = 500, message = "Error from the server"),
                    @ApiResponse(code = 404, message = "Not Found"),
                    @ApiResponse(code = 200, message = "successfully update data")

            })
    @PutMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> update(@RequestBody Student student, @PathVariable String id) {
        if (student.getSName() == null || student.getSName().isEmpty()) {
            return ResponseEntity.badRequest().body("Student name is required.");
        }
        student.setSId(id);
        studentMapper.updateStudent(student);
        return ResponseEntity.status(HttpStatus.OK).body(
                "updated"
        );
    }

    @ApiOperation(value = "get specific student with xml")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 400, message = "Bad Input")
                    ,@ApiResponse(code = 500, message = "Error from the server"),
                    @ApiResponse(code = 404, message = "Not Found"),
                    @ApiResponse(code = 200, message = "successfully selected data")

            })
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Student studentById = studentMapper.getStudentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                studentById
        );
    }


    @ApiOperation(value = "get all student with xml")
    @GetMapping()
    @ApiResponses(value =
            {
                    @ApiResponse(code = 500, message = "Error from the server"),
                    @ApiResponse(code = 200, message = "successfully selected data's")

            })
    public ResponseEntity<?> getAll() {
        List<Student> all = studentMapper.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(
                all
        );
    }

    @ApiOperation(value = "get all student with filter and with xml")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 500, message = "Error from the server"),
                    @ApiResponse(code = 200, message = "successfully selected data"),
                    @ApiResponse(code = 400, message = "Bad Input")

            })
    @GetMapping(
            path = "/filter"
    )
    public ResponseEntity<?> getAllSpecific(@RequestParam(required = false, name = "name")
                                            String name,
                                            @RequestParam(required = false, name = "sex")
                                            String sex) {
        List<Student> all = studentMapper.getAllStudentsByNameAndSex(name, sex);
        Map<String, Object> response = new HashMap<>();
        response.put("student", all);
        response.put("count", all.size());
        return ResponseEntity.status(HttpStatus.OK).body(
                response
        );
    }

    @ApiOperation(value = "delete student with xml")
    @DeleteMapping(
            path = "/{id}"
    )
    @ApiResponses(value =
            {
                    @ApiResponse(code = 400, message = "Bad Input")
                    ,@ApiResponse(code = 500, message = "Error from the server"),
                    @ApiResponse(code = 404, message = "Not Found"),
                    @ApiResponse(code = 200, message = "successfully deleted data")

            })
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        studentMapper.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                "successfully removed data"
        );
    }

    @ApiOperation(value = "get all student with filter and with xml")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 500, message = "Error from the server"),
                    @ApiResponse(code = 200, message = "successfully selected data"),
                    @ApiResponse(code = 400, message = "Bad Input")

            })
    @GetMapping(
            path = "/score"
    )
    public ResponseEntity<?> getAllStudentScoreC1MoreThanC2(@RequestParam(name = "firstCourseId", required = true) Long firstCId,
                                                            @RequestParam(name = "secondCourseId", required = true) Long secondCId) {
        List<StudentAndCourseInfo> all = studentMapper.getStudentsWhoScoreC1MoreThanC2(firstCId, secondCId);
        Map<String, Object> response = new HashMap<>();
        response.put("student", all);
        response.put("count", all.size());
        return ResponseEntity.status(HttpStatus.OK).body(
                response
        );
    }
}
