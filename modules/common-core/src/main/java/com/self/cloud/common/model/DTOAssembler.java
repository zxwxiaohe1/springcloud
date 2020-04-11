package com.self.cloud.common.model;

/**
 * @author HY
 * @date 2018/8/16 10:21
 */
public interface DTOAssembler<T, O> {
    /**
     * 设施日志保存
     *
     * @param o Object 类型
     * @return T
     */
    T adapter(O o);
    /**
     * 设施日志保存
     *
     * @param t T 类型
     * @return O
     */
    O adapterToDto(T t);
}
