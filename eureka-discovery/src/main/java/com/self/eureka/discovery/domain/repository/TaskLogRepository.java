package com.self.eureka.discovery.domain.repository;

import com.self.eureka.discovery.domain.entity.TaskLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * @author HY
 * @date 2019/6/17 10:10
 */
public interface TaskLogRepository extends JpaRepository<TaskLog, Integer> {

    /**
     * 分页查询所有
     * @param specification
     * @param pageable
     * @return
     */
    Page<TaskLog> findAll(Specification<TaskLog> specification, Pageable pageable);

    /**
     * 条件查询所有
     * @param specification
     * @return
     */
    List<TaskLog> findAll(Specification<TaskLog> specification);
}
