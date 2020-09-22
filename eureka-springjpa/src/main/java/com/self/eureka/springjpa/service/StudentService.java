package com.self.eureka.springjpa.service;

import com.self.eureka.springjpa.entity.Student;
import com.self.eureka.springjpa.entity.Teacher;
import com.self.eureka.springjpa.repository.*;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/28 9:30
 */
@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private IdCardRepository idCardRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TeachAndStuRepository teachAndStuRepository;

    public Student get(@NonNull Integer stuId) {
        return studentRepository.getOne(stuId);
    }

    public void save(@NonNull Student student) {
        studentRepository.save(student);
    }

    public void del(@NonNull Integer stuId) {
        teachAndStuRepository.delTchAndStuByStuId(Arrays.asList(stuId));
//        idCardRepository.delStuByStuId(Arrays.asList(stuId));
//        bookRepository.delStuByStuId(Arrays.asList(stuId));
        studentRepository.deleteById(stuId);
    }

    public Student saveOrUpdate(@NonNull Student student) {
        AtomicBoolean exist = new AtomicBoolean(true);
        if (student.getId() == null) {
            exist.set(false);
        }
        Student old = studentRepository.getOne(student.getId());
        if (ObjectUtils.isEmpty(old)) {
            exist.set(false);
        }
        if (exist.get()) {
            BeanUtils.copyProperties(old, student);
        }
        return studentRepository.saveAndFlush(student);
    }
}
