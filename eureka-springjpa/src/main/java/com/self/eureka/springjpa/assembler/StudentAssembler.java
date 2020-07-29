package com.self.eureka.springjpa.assembler;

import com.self.eureka.springjpa.dto.BookDto;
import com.self.eureka.springjpa.dto.GradeDto;
import com.self.eureka.springjpa.dto.StudentDto;
import com.self.eureka.springjpa.dto.TeacherDto;
import com.self.eureka.springjpa.entity.Grade;
import com.self.eureka.springjpa.entity.Student;
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
public class StudentAssembler implements DTOAssembler<Student, StudentDto> {

    @Override
    public Student toEntity(StudentDto studentDto) {
        return null;
    }

    @Override
    public StudentDto toDto(Student student) {
        if (ObjectUtils.isEmpty(student)) {
            return null;
        }
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setAge(student.getAge());
        if (!ObjectUtils.isEmpty(student.getBooks())) {
            List<BookDto> books = new ArrayList<>();
            student.getBooks().stream().forEach(e -> {
                BookDto bookDto = new BookDto();
                bookDto.setId(e.getId());
                bookDto.setName(e.getName());
                bookDto.setPrice(e.getPrice());
                books.add(bookDto);
            });
            studentDto.setBooks(books);
        }
        if (!ObjectUtils.isEmpty(student.getGrades())) {
            List<GradeDto> grades = new ArrayList<>();
            student.getGrades().stream().forEach(e -> {
                GradeDto gradeDto = new GradeDto();
                gradeDto.setId(e.getId());
                gradeDto.setCourse(e.getCourse());
                gradeDto.setLevel(e.getLevel());
                gradeDto.setScore(e.getScore());
                grades.add(gradeDto);
            });
            studentDto.setGrades(grades);
        }
        if (!ObjectUtils.isEmpty(student.getTeachers())) {
            List<TeacherDto> teachers = new ArrayList<>();
            student.getTeachers().stream().forEach(e -> {
                TeacherDto teacherDto = new TeacherDto();
                teacherDto.setId(e.getId());
                teacherDto.setName(e.getName());
                teacherDto.setAge(e.getAge());
                teacherDto.setPosition(e.getPosition());
                teachers.add(teacherDto);
            });
            studentDto.setTeachers(teachers);
        }
        return studentDto;
    }
}
