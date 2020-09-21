package com.self.eureka.springjpa.dto;
import lombok.Data;

/**
 * @author xiaohe
 * @description:
 * @date 2020/9/21 17:06
 */
@Data
public class IdCardDto {

    private Integer id;
    private String num;
    private String remarts;
    private StudentDto student;
}
