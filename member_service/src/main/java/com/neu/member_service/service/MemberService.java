package com.neu.member_service.service;

import com.github.pagehelper.PageInfo;
import com.neu.member_service.domain.Member;
import com.neu.member_service.query.MemberQuery;


public interface MemberService {
    Member login(String loginName,String password);

    Member findById(Integer id);

    int pay(Integer id, String payPassword);

    int add(Member member);

    PageInfo<Member> findByQuery(MemberQuery memberQuery);
}
