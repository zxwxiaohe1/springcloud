package com.self.eureka.springjpa.entity;

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
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11)
    private Integer id;
    @Column(name = "name", length = 10)
    private String name;
    @Column(name = "price", length = 3)
    private Integer price;

    @ManyToMany
    @JoinTable(name = "jpa_stu_book",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "stu_id", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"book_id", "stu_id"})})
    private List<Student> students;
}
