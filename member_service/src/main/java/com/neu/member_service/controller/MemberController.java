package com.neu.member_service.controller;
import com.github.pagehelper.PageInfo;
import com.neu.common.utils.JsonModel;
import com.neu.member_service.domain.Member;
import com.neu.member_service.query.MemberQuery;
import com.neu.member_service.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

@Controller
@RequestMapping("members")
public class MemberController {

    @Autowired
    MemberService memberService;



/*    @RequestMapping("")
    public String login(){
        return "/html/login.html";
    }*/


    @PostMapping("login")
    @ResponseBody
    public Member login(String loginName,String password){
        Member member =  memberService.login(loginName,password);
        //并且携带端口信息
        //member.setName(member.getName());
        return member;
    }

    @PostMapping("pay")
    @ResponseBody
    public JsonModel pay(@RequestBody Member member){

        JsonModel jsonModel = new JsonModel();

        jsonModel.setSuccess(memberService.pay(member.getId(),member.getPayPassword())==1);
        jsonModel.setMsg(jsonModel.isSuccess()?"支付成功":"支付失败");

        return jsonModel;
    }

    @RequestMapping(value="{id}",method = RequestMethod.GET)
    @ResponseBody
    public Member findById (@PathVariable("id") Integer id){
        Member member = memberService.findById(id);
        return member;
    }

    @PostMapping
    @ResponseBody
    public JsonModel add(@RequestBody Member member){

        JsonModel jsonModel = new JsonModel();

        jsonModel.setSuccess(memberService.add(member)==1);
        jsonModel.setMsg(jsonModel.isSuccess()?"注册成功":"注册失败");
        return jsonModel;
    }

    @RequestMapping(value = "/query",method = RequestMethod.POST )
    @ResponseBody
    public JsonModel list(@RequestBody MemberQuery memberQuery){
        JsonModel jsonModel = new JsonModel();
        PageInfo<Member> pageInfo = memberService.findByQuery(memberQuery);
        System.out.println(pageInfo);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("查找用户成功");
        jsonModel.setData(pageInfo.getList());
        jsonModel.setTotal(pageInfo.getTotal());
        return jsonModel;
    }


}
