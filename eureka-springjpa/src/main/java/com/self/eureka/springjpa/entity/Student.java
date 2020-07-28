package com.self.eureka.springjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/28 8:51
 */
@Data
@Entity
@Table(name = "jpa_student")
@JsonInclude
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11)
    private Integer id;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "age", length = 3)
    private Integer age;

    @OneToMany
    @JoinTable(name = "jpa_tch_stu",
            joinColumns = {@JoinColumn(name = "stu_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tch_id", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"stu_id", "tch_id"})})
    private List<Teacher> teachers;

    @OneToOne
    private Grade Grade;
    @ManyToMany
    @JoinTable(name = "jpa_stu_book",
            joinColumns = {@JoinColumn(name = "stu_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"stu_id", "book_id"})})
    private List<Book> books;
}
