package com.self.eureka.springjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @author xiaohe
 * @description:
 * @date 2020/9/21 17:06
 */
@Data
@Entity
@Table(name = "jpa_idcard")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class IdCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11)
    private Integer id;
    @Column(name = "num", length = 18)
    private String num;
    @Column(name = "remarts", length = 50)
    private String remarts;
    @OneToOne(mappedBy = "idCard")
    private Student student;
}
