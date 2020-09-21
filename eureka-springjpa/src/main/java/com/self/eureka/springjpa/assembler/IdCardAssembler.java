package com.self.eureka.springjpa.assembler;

import com.self.eureka.springjpa.dto.IdCardDto;
import com.self.eureka.springjpa.dto.StudentDto;
import com.self.eureka.springjpa.entity.IdCard;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/29 9:51
 */
@Component
public class IdCardAssembler implements DTOAssembler<IdCard, IdCardDto> {

    @Override
    public IdCard toEntity(IdCardDto idCardDto) {
        return null;
    }

    @Override
    public IdCardDto toDto(IdCard idCard) {

        if (ObjectUtils.isEmpty(idCard)) {
            return null;
        }
        IdCardDto idCardDto = new IdCardDto();
        idCardDto.setId(idCard.getId());
        idCardDto.setNum(idCard.getNum());
        idCardDto.setRemarts(idCard.getRemarts());
        if (!ObjectUtils.isEmpty(idCard.getStudent())) {
            StudentDto studentDto = new StudentDto();
            studentDto.setId(idCard.getStudent().getId());
            studentDto.setName(idCard.getStudent().getName());
            studentDto.setAge(idCard.getStudent().getAge());
            idCardDto.setStudent(studentDto);
        }
        return idCardDto;
    }
}
