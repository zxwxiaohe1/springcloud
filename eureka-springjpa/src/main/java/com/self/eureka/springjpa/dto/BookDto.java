package com.self.eureka.springjpa.dto;

import lombok.Data;
import java.util.List;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/28 11:35
 */
@Data
public class BookDto {

    private Integer id;

    private String name;

    private Integer price;

    private List<StudentDto> students;
}
