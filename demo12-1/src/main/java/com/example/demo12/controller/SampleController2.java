package com.example.demo12.controller;

import com.example.demo12.dto.BoardDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController2 {

    @GetMapping(value = "sample2")
    public String sample1(Model model) {

        BoardDTO boarddto = new BoardDTO(1, "board 객체", "작성자1");
        model.addAttribute("boardDTO", boarddto);
        return "sample2";
    }



}
