package com.self.eureka.springjpa.assembler;

import com.self.eureka.springjpa.dto.GradeDto;
import com.self.eureka.springjpa.dto.StudentDto;
import com.self.eureka.springjpa.entity.Grade;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/29 9:51
 */
@Component
public class GradeAssembler implements DTOAssembler<Grade, GradeDto> {

    @Override
    public Grade toEntity(GradeDto gradeDto) {
        return null;
    }

    @Override
    public GradeDto toDto(Grade grade) {

        if (ObjectUtils.isEmpty(grade)) {
            return null;
        }
        GradeDto gradeDto = new GradeDto();
        gradeDto.setId(grade.getId());
        gradeDto.setCourse(grade.getCourse());
        gradeDto.setLevel(grade.getLevel());
        if (!ObjectUtils.isEmpty(grade.getStudent())) {
            StudentDto studentDto = new StudentDto();
            studentDto.setId(grade.getStudent().getId());
            studentDto.setName(grade.getStudent().getName());
            studentDto.setAge(grade.getStudent().getAge());
            gradeDto.setStudent(studentDto);
        }
        return gradeDto;
    }
}
