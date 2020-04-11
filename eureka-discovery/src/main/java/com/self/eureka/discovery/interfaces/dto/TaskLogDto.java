package com.self.eureka.discovery.interfaces.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.self.cloud.common.model.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author HY
 * @date 2019/6/21 13:05
 */
@Setter
@Getter
public class TaskLogDto extends BaseDto<TaskLogDto> {

    private Integer tid;
    /**
     * 任务id
     **/
    private String taskId;
    /**
     * job id
     **/
    private String jobId;
    /**
     * 任务类型
     **/
    private String type;
    /**
     * 任务内容
     **/
    private String content;
    /**
     * 描述
     **/
    private String remark;

    private List<ExtraRemarksDto> paramsRemarks;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime timestamp = LocalDateTime.now();
}
