package com.example.demo12.controller;

import com.example.demo12.dto.MemberDTO;
import com.example.demo12.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SampleController5 {

    @Autowired
    private final MemberService memberService;

    public SampleController5(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping(value = "sample5")
    public String sample5(Model model) {
/*
        MemberDTO memberdto = new MemberDTO(1, "홍길동", "gdhong", "010-1234-1234");

        ArrayList<MemberDTO> arrlist = new ArrayList<MemberDTO>();
        arrlist.add(memberdto);

 */
        List<MemberDTO> arrlist = memberService.getMemberLists();
        model.addAttribute("memberList", arrlist);
        return "sample5";
    }

}
