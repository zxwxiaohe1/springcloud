package com.self.eureka.springjpa.repository;


import com.self.eureka.springjpa.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author HY
 * @date 2020/04/07 10:10
 */
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
