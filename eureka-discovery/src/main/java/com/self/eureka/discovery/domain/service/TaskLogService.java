package com.self.eureka.discovery.domain.service;

import com.self.cloud.common.model.PageParams;
import com.self.eureka.discovery.domain.entity.TaskLog;
import com.self.eureka.discovery.domain.repository.TaskLogRepository;
import com.self.eureka.discovery.interfaces.assembler.TaskLogAssembler;
import com.self.eureka.discovery.interfaces.dto.TaskLogDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HY
 * @date 2019/6/21 11:13
 */
@Slf4j
@Service
@Transactional
@ConfigurationProperties
public class TaskLogService {

    @Autowired
    private TaskLogRepository taskLogRepository;
    @Autowired
    private TaskLogAssembler taskLogAssembler;
    @Autowired
    private EntityManager entityManager;

    /**
     * 添加系统日志
     *
     * @param taskLog TaskLog类型
     * @return Integer
     **/
    public void addTaskLog(TaskLog taskLog) {
        taskLogRepository.save(taskLog);
    }

    /**
     * 添加系统日志
     *
     * @param taskLogDto TaskLog类型
     * @return Integer
     **/
    public void addTaskLog(TaskLogDto taskLogDto) {
        addTaskLog(taskLogAssembler.adapter(taskLogDto));
    }

    /**
     * 分页查询任务日志
     *
     * @param taskLogDto EquipLogDto
     * @return Integer
     **/
    public List<TaskLogDto> findTaskLog(PageParams pageParams, TaskLogDto taskLogDto) {
        return taskLogAssembler.adapterToDto(taskLogRepository.findAll());
    }

    /**
     * 查询设施日志
     *
     * @param taskLogDto FacilityLogDto
     * @return Integer
     **/
    public List<TaskLogDto> findTaskLogMach2(PageParams pageParams, TaskLogDto taskLogDto) {

        Specification<TaskLog> specification = new Specification<TaskLog>() {
            List<Predicate> predicates = new ArrayList<>();

            @Nullable
            @Override
            public Predicate toPredicate(Root<TaskLog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (StringUtils.isNotBlank(taskLogDto.getTaskId())) {
                    predicates.add(criteriaBuilder.equal(root.get("taskId").as(String.class), taskLogDto.getTaskId()));
                }
                Predicate[] predicatesArray = new Predicate[predicates.size()];
                return criteriaBuilder.and(predicates.toArray(predicatesArray));
            }
        };
        return taskLogAssembler.adapterToDto(taskLogRepository.findAll(specification));
    }

    /**
     * 查询设施日志
     *
     * @param taskLogDto FacilityLogDto
     * @return Integer
     **/
    public PageParams<TaskLogDto> pageTaskLogMach(PageParams pageParams, TaskLogDto taskLogDto) {

        Pageable pageable = PageRequest.of(pageParams.pageNo, pageParams.pageSize, Sort.Direction.ASC, "tid");
        Specification<TaskLog> specification = new Specification<TaskLog>() {
            List<Predicate> predicates = new ArrayList<>();

            @Nullable
            @Override
            public Predicate toPredicate(Root<TaskLog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (StringUtils.isNotBlank(taskLogDto.getTaskId())) {
                    predicates.add(criteriaBuilder.equal(root.get("taskId").as(String.class), taskLogDto.getTaskId()));
                }
                Predicate[] predicatesArray = new Predicate[predicates.size()];
                return criteriaBuilder.and(predicates.toArray(predicatesArray));
            }
        };
        Page<TaskLog> pageTtaskLogs = taskLogRepository.findAll(specification, pageable);
        pageParams.setContent(taskLogAssembler.adapterToDto(pageTtaskLogs.getContent()));
        return pageParams;
    }


    /**
     * 查询设施日志
     *
     * @param taskLogDto FacilityLogDto
     * @return Integer
     **/
    public PageParams<TaskLogDto> findTaskLogMachBySql(PageParams pageParams, TaskLogDto taskLogDto) {
        StringBuffer sql = new StringBuffer("select * from test_log_task a inner join (select tid from test_log_task where 1=1 ");
//        StringBuffer countsql = new StringBuffer("select count(id) from bmc_log_task where 1=1 ");
        if (org.apache.commons.lang.StringUtils.isNotBlank(taskLogDto.getTaskId())) {
            sql.append(" and task_id =  '" + taskLogDto.getTaskId() + "'");
//            countsql.append(" and task_id = '" + taskLogDto.getTaskId() + "'");
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(taskLogDto.getType())) {
            sql.append(" and type =  '" + taskLogDto.getType() + "'");
//            countsql.append(" and type = '" + taskLogDto.getType() + "'");
        }
        if (!ObjectUtils.isEmpty(taskLogDto.getStartDate()) && !ObjectUtils.isEmpty(taskLogDto.getEndDate())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            taskLogDto.getStartDate().format(formatter);
            String starttime = taskLogDto.getStartDate().format(formatter);
            String endtime = taskLogDto.getEndDate().format(formatter);
            sql.append(" and timestamp between '" + starttime + "' ").append("and  '" + endtime + "' ");

//            countsql.append(" and timestamp between '" + starttime + "' ").append("and  '" + endtime + "' ");
        }

        Integer start = pageParams.pageNo * pageParams.pageSize;
        sql.append(" order by timestamp desc limit ").append(start + "," + pageParams.pageSize + ")  b on a.tid = b.tid ");
        log.info("sql = " + sql.toString());
//        log.info("countsql = " + countsql.toString());
        Query dataQuery = entityManager.createNativeQuery(sql.toString(), TaskLog.class);
//        Query countQuery = entityManager.createNativeQuery(countsql.toString());
        List<TaskLog> taskLogs = dataQuery.getResultList();
//        pageParams.setCount(Long.valueOf(String.valueOf(countQuery.getSingleResult())));
        pageParams.setContent(taskLogAssembler.adapterToDto(taskLogs));
        return pageParams;
    }
}
