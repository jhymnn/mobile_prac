package com.example.demo12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "스프링 부트 & 타임리프 웹 애플리케이션");
        model.addAttribute("greeting", "안녕하세요! 반갑습니다.");
        model.addAttribute("message", "Spring Boot와 Thymeleaf를 연동한 기본 웹 화면입니다.");
        model.addAttribute("serverTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss")));
        return "index";
    }

    @GetMapping(value = "/test")
    public String test() {
        return "test";
    }


    @PostMapping(value = "/param7")
    public String param7(@RequestBody ItemDTO itemdto, Model model) {
        model.addAttribute("itemname", itemdto.getItemname());
        model.addAttribute("itemprice", itemdto.getItemprice());
        return "/thymeleaf/param7";
    }



    @GetMapping(value = "/param5")
    public String param5(ItemDTO itemdto, Model model) {
        model.addAttribute("username1"
                , itemdto.getItemname());
        model.addAttribute("username2"
                , itemdto.getItemprice());
        return "/thymeleaf/param1";
    }




    @GetMapping(value = "/param1")
    public String param1(@RequestParam("name") String name1,
                         @RequestParam("name") String name2,
                         @RequestParam("name") String name3, Model model) {
        model.addAttribute("data", "데이터");
        model.addAttribute("username1", name1);
        model.addAttribute("username2", name2);
        model.addAttribute("username3", name3);
        return "/thymeleaf/param1";
    }



}
