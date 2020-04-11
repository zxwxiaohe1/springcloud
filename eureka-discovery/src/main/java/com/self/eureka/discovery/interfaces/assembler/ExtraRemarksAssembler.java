package com.self.eureka.discovery.interfaces.assembler;

import com.self.cloud.common.model.DTOAssembler;
import com.self.eureka.discovery.domain.entity.ExtraRemarks;
import com.self.eureka.discovery.interfaces.dto.ExtraRemarksDto;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaohe
 * @description:
 * @date 2020/3/27 10:43
 */
@Component
public class ExtraRemarksAssembler implements DTOAssembler<ExtraRemarks, ExtraRemarksDto> {
    @Override
    public ExtraRemarks adapter(ExtraRemarksDto o) {
        return null;
    }

    @Override
    public ExtraRemarksDto adapterToDto(ExtraRemarks er) {
        if (ObjectUtils.isEmpty(er)) {
            return null;
        }
        ExtraRemarksDto erd = new ExtraRemarksDto();
        erd.setId(er.getEid());
        erd.setRemark(er.getRemark());
        return erd;
    }

    public List<ExtraRemarksDto> adapterToDto(List<ExtraRemarks> ers) {
        if (ObjectUtils.isEmpty(ers)) {
            return null;
        }
        List<ExtraRemarksDto> erds = new ArrayList<>();
        ers.stream().forEach(t -> {
            erds.add(this.adapterToDto(t));
        });
        return erds;
    }
}
