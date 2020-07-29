package com.self.eureka.springjpa.service;

import com.self.eureka.springjpa.repository.TeachAndStuRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/28 16:30
 */
@Service
@Transactional
public class TeachAndStuService {

    @Autowired
    private TeachAndStuRepository teachAndStuRepository;

    public void delByStuId(@NonNull List<Integer> ids) {
        teachAndStuRepository.delTchAndStuByStuId(ids);
    }

}
