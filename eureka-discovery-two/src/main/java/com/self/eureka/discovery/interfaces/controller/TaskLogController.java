package com.self.eureka.discovery.interfaces.controller;

import com.self.cloud.common.model.BaseLogController;
import com.self.cloud.common.model.PageParams;
import com.self.cloud.common.model.ReturnResult;
import com.self.eureka.discovery.domain.service.TaskLogService;
import com.self.eureka.discovery.interfaces.assembler.TaskLogAssembler;
import com.self.eureka.discovery.interfaces.dto.TaskLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HY
 * @date 2019/6/21 12:43
 */
@RestController
@RequestMapping("serverlog")
public class TaskLogController extends BaseLogController {

    @Autowired
    private TaskLogService taskLogService;
    @Autowired
    private TaskLogAssembler taskLogAssembler;


    /**
     * @param groupid
     * @param key
     * @return
     */
    @RequestMapping(value = "/{groupid}/{key}", method = RequestMethod.GET)
    public ReturnResult<String> GetConfigValue(@PathVariable("groupid") String groupid, @PathVariable("key") String key) {
        return success("172.39.8.204" + groupid + key);
    }

    /**
     * 保存任务日志
     *
     * @param taskLogDto EquipLogDto
     * @return ReturnResult
     **/
    @RequestMapping(method = RequestMethod.POST)
    public ReturnResult addTaskLog(@RequestBody TaskLogDto taskLogDto) {
        taskLogService.addTaskLog(taskLogAssembler.adapter(taskLogDto));
        return success("已提交等待处理!");
    }

    /**
     * 调用jpa方法查询所有
     *
     * @param taskLogDto TaskLogDto 类型
     * @return ReturnResult
     */
    @RequestMapping(value = "find", method = RequestMethod.GET)
    public ReturnResult<List<TaskLogDto>> findTaskLog(@ModelAttribute PageParams pageParams, @ModelAttribute TaskLogDto taskLogDto) {
        return success(taskLogService.findTaskLog(pageParams, taskLogDto));
    }

    /**
     * 自定义条件查询所有
     *
     * @param taskLogDto TaskLogDto 类型
     * @return ReturnResult
     */
    @RequestMapping(value = "find2", method = RequestMethod.GET)
    public ReturnResult<List<TaskLogDto>> findTaskLog2(@ModelAttribute PageParams pageParams, @ModelAttribute TaskLogDto taskLogDto) {
        return success(taskLogService.findTaskLogMach2(pageParams, taskLogDto));
    }

    /**
     * 自定义条件并分页查询
     *
     * @param taskLogDto TaskLogDto 类型
     * @return ReturnResult
     */
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public ReturnResult<PageParams<TaskLogDto>> pageTaskLog(@ModelAttribute PageParams pageParams, @ModelAttribute TaskLogDto taskLogDto) {
        return success(taskLogService.pageTaskLogMach(pageParams, taskLogDto));
    }

    /**
     * 自定义条件并分页查询(使用原生SQL)
     *
     * @param taskLogDto TaskLogDto 类型
     * @return ReturnResult
     */
    @RequestMapping(value = "page2", method = RequestMethod.GET)
    public ReturnResult<PageParams<TaskLogDto>> pageTaskLogBySql(@ModelAttribute PageParams pageParams, @ModelAttribute TaskLogDto taskLogDto) {
        return success(taskLogService.findTaskLogMachBySql(pageParams, taskLogDto));
    }

}
