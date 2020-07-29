package com.self.eureka.springjpa.controller;

import com.self.eureka.springjpa.assembler.GradeAssembler;
import com.self.eureka.springjpa.dto.GradeDto;
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
    @Autowired
    private GradeAssembler gradeAssembler;

    @PostMapping
    public String getGrade(@RequestBody Grade grade) {
        gradeService.save(grade);
        return "save success!";
    }

    @GetMapping("{grdId}")
    public GradeDto getGrade(@PathVariable Integer grdId) {
        return gradeAssembler.toDto(gradeService.get(grdId));
    }

    @DeleteMapping("{grdId}")
    public String delGrade(@PathVariable Integer grdId) {
        gradeService.del(grdId);
        return "save success!";
    }
}
