package com.self.eureka.discovery.interfaces.clientinterface.hystrix;

import com.self.cloud.common.model.ReturnResult;
import com.self.eureka.discovery.interfaces.clientinterface.ConfigServerClient;
import com.self.eureka.discovery.interfaces.clientinterface.dto.Result;
import com.self.eureka.discovery.interfaces.dto.TaskLogDto;
import org.springframework.stereotype.Component;

/**
 * @author xiaohe
 */
@Component
public class ConfigServerHystrix implements ConfigServerClient {

    @Override
    public Result GetConfigValue(String groupid, String key) {
        return new Result();
    }

    @Override
    public ReturnResult addTaskLog(TaskLogDto taskLogDto) {
        return new ReturnResult();
    }
}
