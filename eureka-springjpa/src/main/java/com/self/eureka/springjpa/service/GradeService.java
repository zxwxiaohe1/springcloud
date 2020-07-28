package com.self.eureka.springjpa.service;

import com.self.eureka.springjpa.entity.Grade;
import com.self.eureka.springjpa.entity.Student;
import com.self.eureka.springjpa.repository.GradeRepository;
import com.self.eureka.springjpa.repository.StudentRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/28 9:30
 */
@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public Grade get(@NonNull Integer stuId) {
        return gradeRepository.getOne(stuId);
    }

    public void save(@NonNull Grade grade) {
        gradeRepository.save(grade);
    }

    public void del(@NonNull Integer stuId) {
        gradeRepository.deleteById(stuId);
    }
}
