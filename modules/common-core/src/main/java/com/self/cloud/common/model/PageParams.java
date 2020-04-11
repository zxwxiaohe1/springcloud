package com.self.cloud.common.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author heyong
 * @date 2019/11/27
 */
@Setter
@Getter
public class PageParams<T> {
    /*当前页码**/
    public int pageNo = 0;
    /*显示条数**/
    public int pageSize = 10;
    /*总数**/
    public long count;
    /*数据**/
    public T content;
}
