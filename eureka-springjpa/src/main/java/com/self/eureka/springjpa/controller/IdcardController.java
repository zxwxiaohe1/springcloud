package com.self.eureka.springjpa.controller;

import com.self.eureka.springjpa.assembler.IdCardAssembler;
import com.self.eureka.springjpa.dto.BookDto;
import com.self.eureka.springjpa.dto.IdCardDto;
import com.self.eureka.springjpa.entity.Book;
import com.self.eureka.springjpa.entity.IdCard;
import com.self.eureka.springjpa.service.IdCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaohe
 * @description:
 * @date 2020/9/21 17:12
 */
@RestController
@RequestMapping("idcard")
public class IdcardController {

    @Autowired
    private IdCardService idCardService;
    @Autowired
    private IdCardAssembler idCardAssembler;

    @PostMapping
    public String getGrade(@RequestBody IdCard idCard) {
        idCardService.save(idCard);
        return "save success!";
    }

    @GetMapping("{idCardId}")
    public IdCardDto getIdCard(@PathVariable Integer idCardId) {
        return idCardAssembler.toDto(idCardService.get(idCardId));
    }

    @DeleteMapping("{idCardId}")
    public String delIdCard(@PathVariable Integer idCardId) {
        idCardService.del(idCardId);
        return "save success!";
    }
}
