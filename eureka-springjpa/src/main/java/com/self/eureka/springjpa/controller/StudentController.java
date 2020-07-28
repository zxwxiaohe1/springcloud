package com.self.eureka.springjpa.controller;

import com.self.eureka.springjpa.entity.Student;
import com.self.eureka.springjpa.entity.Teacher;
import com.self.eureka.springjpa.service.StudentService;
import com.self.eureka.springjpa.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public String getStudent(@RequestBody Student student) {
        studentService.save(student);
        return "save success!";
    }

    @GetMapping("{stuId}")
    public Student getStudent(@PathVariable Integer stuId) {
        return studentService.get(stuId);
    }

    @DeleteMapping("{stuId}")
    public String delStudent(@PathVariable Integer stuId) {
        studentService.del(stuId);
        return "save success!";
    }
}
