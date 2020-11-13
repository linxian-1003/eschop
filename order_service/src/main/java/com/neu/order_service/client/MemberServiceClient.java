package com.neu.order_service.client;

import com.neu.order_service.fallback.MemberServiceClientFallback;
import com.neu.member_service.domain.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@FeignClient(name = "member-service",fallback = MemberServiceClientFallback.class)

public interface MemberServiceClient {

    @RequestMapping(value="members/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Member findById (@PathVariable("id") Integer id);
}
