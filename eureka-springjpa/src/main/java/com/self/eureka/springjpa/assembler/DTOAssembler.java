package com.self.eureka.springjpa.assembler;

/**
 * @author HY
 * @date 2018/8/16 10:21
 */
public interface DTOAssembler<O, T> {
    /**
     * 转实体
     *
     * @param t T 类型
     * @return T
     */
    O toEntity(T t);

    /**
     * 转Dto
     *
     * @param o O 类型
     * @return O
     */
    T toDto(O o);
}
