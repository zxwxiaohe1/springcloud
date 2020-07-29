package com.self.eureka.springjpa.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/28 8:52
 */
@Data
public class TeacherDto {

    private Integer id;

    private String name;

    private Integer age;

    private String position;

    private List<StudentDto> students;
}
