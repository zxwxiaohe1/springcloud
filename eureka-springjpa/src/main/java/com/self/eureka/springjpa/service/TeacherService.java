package com.self.eureka.springjpa.service;

import com.self.eureka.springjpa.util.BeanUtil;
import com.self.eureka.springjpa.entity.Teacher;
import com.self.eureka.springjpa.repository.TeacherRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/28 9:30
 */
@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher get(@NonNull Integer techId) {
        return teacherRepository.getOne(techId);
    }

    public void save(@NonNull Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void del(@NonNull Integer techId) {
        teacherRepository.deleteById(techId);
    }


    public Teacher saveOrUpdate(@NonNull Teacher teacher) {
        AtomicBoolean exist = new AtomicBoolean(true);
        if (teacher.getId() == null) {
            exist.set(false);
        }
        Teacher old = teacherRepository.getOne(teacher.getId());
        if (ObjectUtils.isEmpty(old)) {
            exist.set(false);
        }
        if (exist.get()) {
            BeanUtil.copyPropertiesIgnoreNull(teacher, old);
        }
        return teacherRepository.saveAndFlush(old);
    }
}
