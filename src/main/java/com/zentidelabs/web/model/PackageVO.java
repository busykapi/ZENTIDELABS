package com.zentidelabs.web.model;

import lombok.Data;

import java.util.Date;

@Data
public class PackageVO {

    private int packageId;
    private String packageType;
    private String packageName;
    private String packageContent;
    private String departAirport;
    private String arrivalAirport;
    private String airline;
    private Date startdate;
    private Date enddate;
    private int fewdays;
    private int childPrice;
    private int adultPrice;
    private int maxCapacity;
    private String guideYn;
    private int hotelgrade;
    private int shoppingCnt;
    private String fileId;
    private String deleteYn;

}
