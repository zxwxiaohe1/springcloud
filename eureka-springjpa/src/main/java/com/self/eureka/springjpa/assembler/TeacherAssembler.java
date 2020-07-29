package com.self.eureka.springjpa.assembler;

import com.self.eureka.springjpa.dto.GradeDto;
import com.self.eureka.springjpa.dto.StudentDto;
import com.self.eureka.springjpa.dto.TeacherDto;
import com.self.eureka.springjpa.entity.Grade;
import com.self.eureka.springjpa.entity.Teacher;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/29 9:51
 */
@Component
public class TeacherAssembler implements DTOAssembler<Teacher, TeacherDto> {

    @Override
    public Teacher toEntity(TeacherDto teacherDto) {
        return null;
    }

    @Override
    public TeacherDto toDto(Teacher teacher) {

        if (ObjectUtils.isEmpty(teacher)) {
            return null;
        }
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setName(teacher.getName());
        teacherDto.setAge(teacher.getAge());
        teacherDto.setPosition(teacher.getPosition());
        if (!ObjectUtils.isEmpty(teacher.getStudents())) {
            List<StudentDto> stus = new ArrayList<>();
            teacher.getStudents().stream().forEach(e -> {
                StudentDto studentDto = new StudentDto();
                studentDto.setId(e.getId());
                studentDto.setName(e.getName());
                studentDto.setAge(e.getAge());
                stus.add(studentDto);
            });
            teacherDto.setStudents(stus);
        }
        return teacherDto;
    }
}
