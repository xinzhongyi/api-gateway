package com.dh.cloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.context.annotation.Configuration;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by dh on 2018/11/6.
 */
@WebFilter
@Configuration
public class AccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext() ;
        HttpServletRequest request = requestContext.getRequest() ;

        Object accessToken = request.getParameter("accessToken");
        if(accessToken == null){
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            return null ;
        }
        return null;
    }
}
