package com.self.eureka.discovery.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author xiaohe
 * @description:
 * @date 2020/3/27 10:21
 */
@Setter
@Getter
@Entity
@Table(name = "test_extra_remarks")
public class ExtraRemarks {

    @Id
    @GeneratedValue
    @Column
    private int eid;

    /**
     * 描述
     **/
    @Column(length = 254)
    private String remark;

    /**
     * 如果不指定@JoinColumn那么则根据@ManyToOne注解的属性加上一的一端主键名规则化成外键属性
     */
    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "tid" )
    private TaskLog taskLog;
}
