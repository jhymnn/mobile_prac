package com.example.demo12.service;

import com.example.demo12.dto.MemberDTO;
import com.example.demo12.model.Member;
import com.example.demo12.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    //@Autowired 사용 지양됨 -> @RequiredArgsConstructor 로 생성되는 생성자로 주입받기 위해 final 붙임.
    private final MemberRepository memberRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<MemberDTO> getMemberLists(){
        List<Member> listMembers;
        listMembers = memberRepository.findAll();

        //1번
        List<MemberDTO> resultList = listMembers.stream().map(member -> modelMapper.map(member, MemberDTO.class)).collect(Collectors.toList());
/*
        //2번
        List<MemberDto> resultList = Arrays.asList(modelMapper.map(postList,PostResponseDto[].class));
*/

        return resultList;
    }



}
