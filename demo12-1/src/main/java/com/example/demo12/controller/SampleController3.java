package com.example.demo12.controller;

import com.example.demo12.dto.BoardDTO;
import com.example.demo12.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Slf4j
@Controller
public class SampleController3 {

    @GetMapping(value = "sample3")
    public String sample3(Model model) {

        //MemberDTO memberdto = new MemberDTO(1, "홍길동", "gdhong", "010-1234-1234");

        MemberDTO memberdto = new MemberDTO();
        memberdto.setNum(1);
        memberdto.setId("gdhone");
        memberdto.setName("홍길동");
        memberdto.setPhone("010-1234-1234");

        ArrayList<MemberDTO> arrlist = new ArrayList<MemberDTO>();
        arrlist.add(memberdto);
        model.addAttribute("memberList", arrlist);
        return "sample3";
    }

    @GetMapping(value = "/member/memberDeleteOk")
    public String delete(@RequestParam( value = "num", required=false) Integer num, Model model) {

        if (num == null) {
            System.out.println("null 입니다");
            log.trace("trace log={}", num);
            log.debug("debug log={}", num);
            log.info(" info log={}", num);
            log.warn(" warn log={}", num);
            log.error("error log={}", num);
            return "redirect:/member/memberList";


        }

        System.out.println(num);

        log.trace("trace log={}", num);
        log.debug("debug log={}", num);
        log.info(" info log={}", num);
        log.warn(" warn log={}", num);
        log.error("error log={}", num);

        try {

            // 삭제 동작

        } catch (Exception e) {

        }



        return "/member/messageAlert";
    }



}
