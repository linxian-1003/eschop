package com.neu.member_service.dao;

import com.neu.member_service.domain.Member;
import com.neu.member_service.query.MemberQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDao {

    Member login(String loginName, String password);

    Member findById(Integer id);

    String findPayPasswordById(Integer id);

    int add(Member member);

    List<Member> findByQuery(MemberQuery memberQuery);
}
