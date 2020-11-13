package com.neu.member_service.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.member_service.dao.MemberDao;
import com.neu.member_service.domain.Member;
import com.neu.member_service.query.MemberQuery;
import com.neu.member_service.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberDao memberDao;


    @Override
    public Member login(String loginName, String password) {
        return memberDao.login(loginName,password);
    }

    @Override
    public Member findById(Integer id) {
        Member member = memberDao.findById(id);
        return member;
    }

    @Override
    public int pay(Integer id, String payPassword) {
        String realPayPassword = memberDao.findPayPasswordById(id);
        if(realPayPassword.equals(payPassword)){
            return 1;
        }
        return 0;
    }

    @Override
    public int add(Member member) {
        return memberDao.add(member);
    }

    @Override
    public PageInfo<Member> findByQuery(MemberQuery memberQuery) {
        PageInfo<Member> pageInfo=null;
        if(memberQuery.isPaging()){
            PageHelper.startPage(memberQuery.getPageNum(),memberQuery.getPageSize());
            Page<Member> page = (Page<Member>) memberDao.findByQuery(memberQuery);
            pageInfo = page.toPageInfo();
        }else{
            List<Member> data = memberDao.findByQuery(memberQuery);
            pageInfo = new PageInfo<>();
            pageInfo.setList(data);
        }
        return pageInfo;
    }
}
