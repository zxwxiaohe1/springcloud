package com.self.eureka.springjpa.entity;

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
public class Grade {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(length = 11)
    private Integer id;
    @Column(name = "course", length = 10)
    private String course;
    @Column(name = "score", length = 3)
    private Integer score;
    @Column(name = "level", length = 10)
    private String level;

    @OneToOne
    private Student student;
}
