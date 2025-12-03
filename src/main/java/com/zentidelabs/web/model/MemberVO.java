package com.zentidelabs.web.model;

import lombok.Data;

@Data
public class MemberVO {

    private int memberId;
    private String userId;
    private String pwd;
    private String name;
    private int birthday;
    private String phonenumber;
    private String email;
    private String address;
    private String addressDetail;
    private int zipcode;
    private String route;
    private String status;
    private String adminYn;

}
