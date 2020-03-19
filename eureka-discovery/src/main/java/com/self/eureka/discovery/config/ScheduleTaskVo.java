package com.self.eureka.discovery.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author heyong
 * @date 2019/8/17
 */

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "bmc.log-server.schedule.log.clear")
public class ScheduleTaskVo {
    private Boolean enable;
    private String time;
    private String backupPath;
    private Long retain;
    private String tables;
}