package com.self.eureka.springjpa.controller;

import com.self.eureka.springjpa.assembler.StudentAssembler;
import com.self.eureka.springjpa.dto.StudentDto;
import com.self.eureka.springjpa.entity.Student;
import com.self.eureka.springjpa.entity.Teacher;
import com.self.eureka.springjpa.service.StudentService;
import com.self.eureka.springjpa.service.TeachAndStuService;
import com.self.eureka.springjpa.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author xiaohe
 * @description:
 * @date 2020/3/17 17:19
 */
@RestController
@RequestMapping("stu")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeachAndStuService teachAndStuService;
    @Autowired
    private StudentAssembler studentAssembler;

    @PostMapping
    public String getStudent(@RequestBody Student student) {
        studentService.save(student);
        return "save success!";
    }

    @GetMapping("{stuId}")
    public StudentDto getStudent(@PathVariable Integer stuId) {
        return studentAssembler.toDto(studentService.get(stuId));
    }

    @DeleteMapping("{stuId}")
    public String delStudent(@PathVariable Integer stuId) {
        studentService.del(stuId);
        return "save success!";
    }

    @PutMapping
    public String updateStudent(@RequestBody Student student) {
        studentService.saveOrUpdate(student);
        return "save success!";
    }

    @DeleteMapping("nev/{stuId}")
    public String delNevStudent(@PathVariable Integer stuId) {
        teachAndStuService.delByStuId(Arrays.asList(stuId));
        return "save success!";
    }
}
