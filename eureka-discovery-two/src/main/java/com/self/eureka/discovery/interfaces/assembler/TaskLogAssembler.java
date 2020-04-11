package com.self.eureka.discovery.interfaces.assembler;

import com.self.cloud.common.model.DTOAssembler;
import com.self.eureka.discovery.domain.entity.TaskLog;
import com.self.eureka.discovery.interfaces.dto.TaskLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HY
 * @date 2019/6/21 11:13
 */
@Component
public class TaskLogAssembler implements DTOAssembler<TaskLog, TaskLogDto> {

    @Autowired
    private ExtraRemarksAssembler extraRemarksAssembler;

    @Override
    public TaskLog adapter(TaskLogDto tld) {
        if (ObjectUtils.isEmpty(tld)) {
            return null;
        }
        TaskLog tl = new TaskLog();
        tl.setType(tld.getType());
        tl.setTaskId(tld.getTaskId());
        tl.setContent(tld.getContent());
        tl.setJobId(tld.getJobId());
        tl.setRemark(tld.getRemark());
        if (!ObjectUtils.isEmpty(tld.getTimestamp())) {
            tl.setTimestamp(tld.getTimestamp());
        }
        return tl;
    }

    @Override
    public TaskLogDto adapterToDto(TaskLog tl) {
        if (ObjectUtils.isEmpty(tl)) {
            return null;
        }
        TaskLogDto tld = new TaskLogDto();
        tld.setTid(tl.getTid());
        tld.setType(tl.getType());
        tld.setTaskId(tl.getTaskId());
        tld.setContent(tl.getContent());
        tld.setRemark(tl.getRemark());
        tld.setJobId(tl.getJobId());
        if (!ObjectUtils.isEmpty(tl.getTimestamp())) {
            tld.setTimestamp(tl.getTimestamp());
        }
        if (!ObjectUtils.isEmpty(tl.getParamRemarks())) {
            tld.setParamsRemarks(extraRemarksAssembler.adapterToDto(tl.getParamRemarks()));
        }
        return tld;
    }

    public List<TaskLogDto> adapterToDto(List<TaskLog> tls) {
        if (ObjectUtils.isEmpty(tls)) {
            return null;
        }
        List<TaskLogDto> tlds = new ArrayList<>();
        tls.stream().forEach(t -> {
            tlds.add(this.adapterToDto(t));
        });
        return tlds;
    }
}
