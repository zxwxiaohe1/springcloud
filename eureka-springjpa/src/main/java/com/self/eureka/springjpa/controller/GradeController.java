package com.self.eureka.springjpa.controller;

import com.self.eureka.springjpa.entity.Grade;
import com.self.eureka.springjpa.entity.Student;
import com.self.eureka.springjpa.service.GradeService;
import com.self.eureka.springjpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaohe
 * @description:
 * @date 2020/3/17 17:19
 */
@RestController
@RequestMapping("grd")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @PostMapping
    public String getGrade(@RequestBody Grade grade) {
        gradeService.save(grade);
        return "save success!";
    }

    @GetMapping("{grdId}")
    public Grade getGrade(@PathVariable Integer grdId) {
        return gradeService.get(grdId);
    }

    @DeleteMapping("{grdId}")
    public String delGrade(@PathVariable Integer grdId) {
        gradeService.del(grdId);
        return "save success!";
    }
}
