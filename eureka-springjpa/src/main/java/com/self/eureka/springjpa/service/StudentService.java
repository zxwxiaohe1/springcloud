package com.self.eureka.springjpa.service;

import com.self.eureka.springjpa.entity.Student;
import com.self.eureka.springjpa.entity.Teacher;
import com.self.eureka.springjpa.repository.StudentRepository;
import com.self.eureka.springjpa.repository.TeacherRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/28 9:30
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student get(@NonNull Integer stuId) {
        return studentRepository.getOne(stuId);
    }

    public void save(@NonNull Student student) {
        studentRepository.save(student);
    }

    public void del(@NonNull Integer stuId) {
        studentRepository.deleteById(stuId);
    }
}
