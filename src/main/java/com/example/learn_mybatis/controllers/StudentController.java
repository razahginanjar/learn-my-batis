package com.example.learn_mybatis.controllers;

import com.example.learn_mybatis.entities.Student;
import com.example.learn_mybatis.services.StudentServiceNoXml;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/student")
@Api(tags = "Student Management", value = "Student Controller" )
@RequiredArgsConstructor
public class StudentController {
    private final StudentServiceNoXml studentMapper;

    @ApiOperation(value = "create student no xml")
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
        studentMapper.create(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                "created"
        );
    }

    @ApiResponses(value =
            {
                    @ApiResponse(code = 400, message = "Bad Input")
                    ,@ApiResponse(code = 500, message = "Error from the server"),
                    @ApiResponse(code = 200, message = "successfully updated data")

            })
    @ApiOperation(value = "update student no xml")
    @PutMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> update(@RequestBody Student student, @PathVariable String id) {
        if (student.getSName() == null || student.getSName().isEmpty()) {
            return ResponseEntity.badRequest().body("Student name is required.");
        }
        student.setSId(id.toString());
        studentMapper.update(student);
        return ResponseEntity.status(HttpStatus.OK).body(
                "created"
        );
    }

    @ApiResponses(value =
            {
                    @ApiResponse(code = 400, message = "Bad Input"),
                    @ApiResponse(code = 404, message = "Not Found")
                    ,@ApiResponse(code = 500, message = "Error from the server"),
                    @ApiResponse(code = 200, message = "successfully inserted data")

            })
    @ApiOperation(value = "get student no xml")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        Student studentById = studentMapper.getStudentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                studentById
        );
    }

    @ApiResponses(value =
            {
                    @ApiResponse(code = 400, message = "Bad Input")
                    ,@ApiResponse(code = 500, message = "Error from the server"),
                    @ApiResponse(code = 404, message = "Not Found"),
                    @ApiResponse(code = 200, message = "successfully inserted data")

            })
    @ApiOperation(value = "delete student no xml")
    @DeleteMapping(
            path = "/{id}"
    )
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        studentMapper.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                "successfully removed data"
        );
    }
}
