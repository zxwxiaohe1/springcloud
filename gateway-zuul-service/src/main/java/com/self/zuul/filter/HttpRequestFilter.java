package com.self.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.self.cloud.common.utils.ConstantUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * @author xiaohe
 * @description:
 * @date 2020/3/18 14:06
 */
@Component
public class HttpRequestFilter extends ZuulFilter {


    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String remoteAddr = request.getRemoteAddr();
        ctx.getZuulRequestHeaders().put(ConstantUtils.HTTP_X_FORWARDED_FOR, remoteAddr);
        return null;
    }

    /**
     * pre：主要用在路由映射的阶段是寻找路由映射表的
     * routing:具体的路由转发过滤器是在routing路由器，具体的请求转发的时候会调用
     * error:一旦前面的过滤器出错了，会调用error过滤器。
     * post:当routing，error运行完后才会调用该过滤器，是在最后阶段的
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 900;
    }

    /**
     * 控制过滤器生效不生效，可以在里面写一串逻辑来控制,true是生效
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }
}
