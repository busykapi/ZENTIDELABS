package com.zentidelabs.web.model;

import lombok.Data;

import java.util.Date;

@Data
public class ReserveVO {

    private int reserveId;
    private int price;
    private String paymentType;
    private int childNum;
    private int adultNum;
    private Date regdate;
    private String reserveStatus;
    private int packageId;
    private int memberId;

}
