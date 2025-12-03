package com.zentidelabs.web.model;

import lombok.Data;

import java.util.Date;

@Data
public class FileVO {

    private String fileId;
    private int fileOrder;
    private String fileName;
    private String fileOriginName;
    private int size;
    private String path = "C:\\Users\\tok_o\\MyIntelliJ\\YZtourWeb\\src\\main\\resources\\savefiles\\";
    private Date regdate;
    private String deleteYn;

}
