package com.example.demo12.model;

import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="member")
@ToString
public class Member {

    @Id
    /*
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    */
    @Column(name = "NUM")
    private long num;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 20)
    private String id;
    @Column(nullable = false, length = 12)
    private String phone;
    @Column(nullable = false, unique = true)
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}