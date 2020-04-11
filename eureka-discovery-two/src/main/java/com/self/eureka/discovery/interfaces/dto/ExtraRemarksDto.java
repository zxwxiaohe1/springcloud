package com.self.eureka.discovery.interfaces.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaohe
 * @description:
 * @date 2020/3/27 10:42
 */
@Setter
@Getter
public class ExtraRemarksDto {

    private int id;

    /**
     * 任务id(主键)
     **/
    private int taskId;

    /**
     * 描述
     **/
    private String remark;
}
