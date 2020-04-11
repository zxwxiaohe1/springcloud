package com.self.eureka.discovery.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.self.cloud.common.model.PageParams;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

/**
 * @author HY
 * @date 2019/6/17 10:45
 */
@Setter
@Getter
public abstract class BaseEntity<T> {
    /**
     * 分页参数
     **/
    protected PageParams<T> page = new PageParams();

    /**
     * 字段校验
     *
     * @return T
     */
    public abstract T volidate();

    @JsonIgnore
    public Sort getSort() {
        return Sort.by(Sort.Direction.DESC, "id");
    }

}
