package com.self.eureka.springjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/28 8:52
 */
@Data
@Entity
@Table(name = "jpa_teacher")
@JsonInclude
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11)
    private Integer id;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "age", length = 3)
    private Integer age;
    @Column(name = "position", length = 10)
    private String position;
    @OneToMany
    @JoinTable(name = "jpa_tch_stu",
            joinColumns = {@JoinColumn(name = "tch_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "stu_id", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"tch_id", "stu_id"})})
    private List<Student> students;
}
