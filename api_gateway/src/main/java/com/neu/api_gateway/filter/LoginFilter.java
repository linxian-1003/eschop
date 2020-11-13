package com.neu.api_gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 需求:当前Filter拦截请求商品服务（如果当前请求头有token放行,没有拦截）
 */
//@Component
public class LoginFilter extends ZuulFilter {

    //设置Filter的类型（pre）
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 指定Filter的顺序（值越小越先执行）
     * @return
     */
    @Override
    public int filterOrder() {
        return 6;
    }



    //是否要拦截
    @Override
    public boolean shouldFilter() {
        //当前Filter拦截请求商品服务（如果当前请求头有token放行,没有拦截）

        //获取当前请求路径
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String requestURI = request.getRequestURI();
        if(requestURI.startsWith("/apiGateway/book")){
            //当前请求请求商品服务
            return true;  //代表需要拦截
        }else{
            return false;
        }

    }

    //用来处理业务逻辑
    @Override
    public Object run() throws ZuulException {

        //判断当前请求是否有token请求头

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getHeader("token");
        if(token == null || "".equals(token)){
            //拒绝往下执行
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }

    //Filter的类型应该是Pre

    //定义Zuul Filter的步骤
    //
/*
    |--新建类继承ZuulFilter抽象类 \实现未实现的方法
|--添加@Component注解
|--设置过滤器类型(设置前置即可)
|--设置顺序,过滤器越小越先执行
|--设置过滤器是否生效(shouldFilter)
        |--RequestContext.getCurrentContext()
        |--根据RequestContext获取请求URI，根据逻辑判断是否要放行
|--编写业务逻辑run
|--拒绝请求往下执行
requestContext.setSendZuulResponse(false);
//设置状态编码
requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value())*/


}
