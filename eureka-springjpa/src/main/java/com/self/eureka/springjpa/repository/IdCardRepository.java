package com.self.eureka.springjpa.repository;

import com.self.eureka.springjpa.entity.IdCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author HY
 * @date 2020/04/07 10:10
 */
public interface IdCardRepository extends JpaRepository<IdCard, Integer> {
    @Query(value = "delete from jpa_stu_idcard where stu_id in(?1)", nativeQuery = true)
    Integer delStuByStuId(List<Integer> ids);
}
