package com.self.cloud.common.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * @author HY
 * @date 2019/6/17 10:45
 */
@Setter
@Getter
public abstract class BaseDto<T> {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
