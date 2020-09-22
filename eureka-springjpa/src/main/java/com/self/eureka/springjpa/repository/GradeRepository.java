package com.self.eureka.springjpa.repository;


import com.self.eureka.springjpa.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author HY
 * @date 2020/04/07 10:10
 */
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    @Modifying
    @Query(value = "delete from jpa_stu_grad where stu_id in(?1)", nativeQuery = true)
    Integer deltuByStuId(List<Integer> ids);
}
