package com.self.eureka.discovery.controller;

import com.self.cloud.common.utils.ConstantUtils;
import com.self.eureka.discovery.config.ScheduleTaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaohe
 * @description:
 * @date 2020/3/17 17:19
 */
@RestController
@RequestMapping("test")
public class Test {

    @Autowired
    private ScheduleTaskVo scheduleTaskVo;

    @GetMapping("get")
    public Map get(HttpServletRequest request) {
        Map<String, Object> viewValue = new HashMap<>();
        //cookies值测试
        Cookie[] cookies = request.getCookies();
        if (!ObjectUtils.isEmpty(cookies)) {
            Map<String, Object> cookiesMap = new HashMap<>();
            for (Cookie c : cookies) {
                cookiesMap.put(c.getName(), c.getValue());
            }
            viewValue.put("cookies", cookiesMap);
        }

        viewValue.put("schedule-task", scheduleTaskVo);
        viewValue.put("really-ip", request.getHeader(ConstantUtils.HTTP_X_FORWARDED_FOR));
        return viewValue;
    }
}
