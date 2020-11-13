package com.neu.api_gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.neu.api_gateway.config.SysConfig;
import com.neu.auth_common.JWTUtil;
import com.neu.auth_common.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 用来判断是否登录(根据cookie中的token)
 */
@Component
public class TokenFilter extends ZuulFilter {

    @Autowired
    SysConfig sysConfig;

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
        //如果是来登录的,不需要拦截
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String requestURI = request.getRequestURI();

        //客户端没有登录除了请求登录资源还应该能请求商品类型等的信息

        //白名单
        //哪些要放行

        String exclude = sysConfig.getExclude();

        if(exclude!=null){
            String [] excludes = exclude.split(",");
            for(String tempExclude: excludes){
                if(requestURI.startsWith(tempExclude)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 拦截的业务处理
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        //判断cookie中有没有名为token
        Cookie cookies[] = request.getCookies();
        Cookie tokenCookie = null;


        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    tokenCookie = cookie;
                    break;
                }
            }
        }

        if (tokenCookie == null) {
            //拒绝往下执行
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        } else {
            //找到了该cookie
            String value = tokenCookie.getValue(); //token的值
            //校验value是否为合法的token

            try {
                //根据token获取原始信息
                JWTUtil.getInfoFromToken(value, RSAUtil.getPublicKey(sysConfig.getPublicKeyPath()));
            } catch (Exception e) {
                e.printStackTrace();
                //token被人篡改了
                //拒绝往下执行
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }
        }
        return null;
    }


}
