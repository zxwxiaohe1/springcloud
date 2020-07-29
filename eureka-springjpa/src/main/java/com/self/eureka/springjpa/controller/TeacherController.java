package com.self.eureka.springjpa.controller;

import com.self.eureka.springjpa.assembler.TeacherAssembler;
import com.self.eureka.springjpa.dto.TeacherDto;
import com.self.eureka.springjpa.entity.Teacher;
import com.self.eureka.springjpa.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaohe
 * @description:
 * @date 2020/3/17 17:19
 */
@RestController
@RequestMapping("tech")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherAssembler teacherAssembler;

    @PostMapping
    public String getTeacher(@RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return "save success!";
    }

    @GetMapping("{techId}")
    public TeacherDto getTeacher(@PathVariable Integer techId) {
        return teacherAssembler.toDto(teacherService.get(techId));
    }

    @DeleteMapping("{techId}")
    public String delTeacher(@PathVariable Integer techId) {
        teacherService.del(techId);
        return "save success!";
    }

    @PutMapping
    public String updateStudent(@RequestBody Teacher teacher) {
        Teacher newTeach = teacherService.saveOrUpdate(teacher);
        return "save success!";
    }
}
