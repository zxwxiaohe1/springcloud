package com.self.eureka.springjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/28 8:58
 */
@Data
@Entity
@Table(name = "jpa_grade")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11)
    private Integer id;
    @Column(name = "course", length = 10)
    private String course;
    @Column(name = "score", length = 3)
    private Integer score;
    @Column(name = "level", length = 10)
    private String level;
    @OneToOne
    @JoinTable(name = "jpa_stu_grad",
            joinColumns = {@JoinColumn(name = "grad_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "stu_id", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"grad_id", "stu_id"})})
    private Student student;
}
