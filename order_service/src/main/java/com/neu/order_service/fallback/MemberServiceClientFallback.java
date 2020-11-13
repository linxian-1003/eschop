package com.neu.order_service.fallback;

import com.neu.order_service.client.MemberServiceClient;
import org.springframework.stereotype.Component;
import com.neu.member_service.domain.Member;

@Component
public class MemberServiceClientFallback implements MemberServiceClient {


    @Override
    public Member findById(Integer MemberId) {
        Member member = new Member();
        member.setId(MemberId);
        member.setName(MemberId+"找不到会员");
        return member;
    }
}
