package com.self.eureka.springjpa.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/28 8:58
 */
@Data
public class GradeDto {

    private Integer id;
    private String course;
    private Integer score;
    private String level;
    private StudentDto student;
}
