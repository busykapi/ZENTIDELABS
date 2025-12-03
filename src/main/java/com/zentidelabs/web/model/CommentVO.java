package com.zentidelabs.web.model;


import lombok.Data;

import java.util.Date;

@Data
public class CommentVO {

    private int commentId;
    private String content;
    private Date regdate;
    private int commentOrder;
    private int commentLevel;
    private int commentParentId;
    private int memberId;
    private int boardId;
    private String deleteYn;

}
