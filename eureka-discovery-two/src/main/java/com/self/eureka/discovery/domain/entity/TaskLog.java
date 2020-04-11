package com.self.eureka.discovery.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author HY
 * @date 2019/7/15 10:12
 */
@Setter
@Getter
@Entity
@Table(name = "test_log_task")
public class TaskLog extends BaseEntity<TaskLog> {
    @Id
    @GeneratedValue
    @Column
    private int tid;
    /**
     * 任务id
     **/
    @Column(length = 36)
    private String taskId;
    /**
     * 任务id
     **/
    @Column(length = 36)
    private String jobId;
    /**
     * 任务类型
     **/
    @Column(length = 20)
    private String type;
    /**
     * 任务内容
     **/
    @Column(length = 500)
    private String content;
    /**
     * 描述
     **/
    @Column(length = 500)
    private String remark;
    /**
     * 时间戳
     **/
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column
    private LocalDateTime timestamp = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskLog",fetch = FetchType.LAZY)
    List<ExtraRemarks> paramRemarks;

    @Override
    public TaskLog volidate() {
        if (this.content != null && this.content.length() > 500) {
            this.content = this.content.substring(0, 500);
        }
        if (this.remark != null && this.remark.length() > 500) {
            this.remark = this.remark.substring(0, 500);
        }
        return this;
    }
}
