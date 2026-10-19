package com.example.demo12.dto;

import com.example.demo12.model.Member;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {
    private long num;
    private String name;
    private String id;
    private String phone;

    /*
    public MemberDTO(long num, String name, String id, String phone) {
        this.num = num;
        this.name = name;
        this.id = id;
        this.phone = phone;
    }

    public MemberDTO(Member member) {
        this.num = member.getNum();
        this.name = member.getName();
        this.id = member.getId();
        this.phone = member.getPhone();
    }


    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

 */
}
