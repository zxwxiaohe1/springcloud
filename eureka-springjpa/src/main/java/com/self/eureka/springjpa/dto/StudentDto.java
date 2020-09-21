package com.self.eureka.springjpa.dto;

import lombok.Data;

import java.util.List;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/28 8:51
 */
@Data
public class StudentDto {

    private Integer id;

    private String name;

    private Integer age;

    private List<TeacherDto> teachers;
    private List<GradeDto> grades;
    private List<BookDto> books;
    private IdCardDto idCard;
}
