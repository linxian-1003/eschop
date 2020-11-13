package com.neu.auth_service.client;

import com.neu.member_service.domain.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Feign接口
 *    注意:
 *       接口的方法什么要和对应控制器的方法声明保持一致
 */
@FeignClient(name = "member-service")
//name的值是服务提供方的应用名字
public interface MemberServiceClient {

    //注意点
    //这里的方法什么要和对应控制器的方法声明保持一致
    //如下规则
/*       1、路径
	2、Http方法必须对应
	3、使用requestBody，应该使用@PostMapping
	4、多个参数的时候，通过@RequestParam（"id") int id)方式调用
5、使用路径传递参数是,通过@PathVariable(“id”) int id方式调用
6、使用RequestMethod(method=get|post)注解*/


    @RequestMapping(value="members/login",method = RequestMethod.POST)
    @ResponseBody
    public Member login(@RequestParam("loginName") String loginName,@RequestParam("password")String password);
}
