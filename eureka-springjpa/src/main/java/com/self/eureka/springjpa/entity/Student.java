package com.self.eureka.springjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/28 8:51
 */
@Setter
@Getter
@Entity
@Table(name = "jpa_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11)
    private Integer id;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "age", length = 3)
    private Integer age;

    @ManyToMany(mappedBy = "students")
    private List<Teacher> teachers;

    @OneToMany
    @JoinTable(name = "jpa_stu_grad",
            joinColumns = {@JoinColumn(name = "stu_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "grad_id", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"stu_id", "grad_id"})})
    private List<Grade> grades;

    @ManyToMany
    @JoinTable(name = "jpa_stu_book",
            joinColumns = {@JoinColumn(name = "stu_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"stu_id", "book_id"})})
    private List<Book> books;
}
