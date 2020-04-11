package com.self.eureka.discovery.interfaces.clientinterface;

import com.self.cloud.common.model.ReturnResult;
import com.self.eureka.discovery.interfaces.clientinterface.dto.Result;
import com.self.eureka.discovery.interfaces.dto.TaskLogDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author xiaohe
 */
@FeignClient(value = "eureka-discovery-two")
public interface ConfigServerClient {
    /**
     * @param groupid
     * @param key
     * @return
     */
    @RequestMapping(value = "/serverlog/{groupid}/{key}", method = RequestMethod.GET)
    Result GetConfigValue(@PathVariable("groupid") String groupid, @PathVariable("key") String key);

    /**
     * 保存任务日志
     *
     * @param taskLogDto EquipLogDto
     * @return ReturnResult
     **/
    @RequestMapping(value = "/serverlog",method = RequestMethod.POST)
    ReturnResult addTaskLog(@RequestBody TaskLogDto taskLogDto);
}
