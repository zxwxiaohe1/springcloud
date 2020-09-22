package com.self.eureka.springjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/28 11:35
 */
@Data
@Entity
@Table(name = "jpa_book")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11)
    private Integer id;
    @Column(name = "name", length = 10)
    private String name;
    @Column(name = "price", length = 3)
    private Integer price;
    @ManyToMany(mappedBy = "books")
    private List<Student> students;
}
