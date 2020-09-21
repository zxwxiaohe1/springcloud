package com.self.eureka.springjpa.service;

import com.self.eureka.springjpa.entity.IdCard;
import com.self.eureka.springjpa.repository.IdCardRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/28 9:30
 */
@Service
public class IdCardService {

    @Autowired
    private IdCardRepository idCardRepository;

    public IdCard get(@NonNull Integer stuId) {
        return idCardRepository.getOne(stuId);
    }

    public void save(@NonNull IdCard idCard) {
        idCardRepository.save(idCard);
    }

    public void del(@NonNull Integer stuId) {
        idCardRepository.deleteById(stuId);
    }
}
