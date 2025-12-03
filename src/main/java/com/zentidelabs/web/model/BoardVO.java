package com.zentidelabs.web.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BoardVO {

    private int boardId;
    private String title;
    private String content;
    private Date regdate;
    private int hit;
    private String pubYn;
    private String category;
    private String fileId;
    private String deleteYn;
    private int memberId;


    /*검색*/
    private String searchCondition;
    private String searchKeyword;

    /*페이지쿼리*/
    private int pageNum = 1;
    private int size = 10;
    private int limit;
    private int offset;
    private int totalCount;
    private int totalPage;
    private int displayPageNum=2;
    private int startPage;
    private int endPage;
    private String name;

    /*첨부파일 정보*/
    List<FileVO> fileList;

    public int getTotalPage() {
        return (int) Math.ceil(totalCount/10.0);
    }

    public int getLimit() {
        return size;
    }

    public int getOffset() {
        return pageNum == 1 ? 0 : ((pageNum-1) * size);
    }

    public int getStartPage() {
        return (endPage - displayPageNum) + 1;
    }

    public int getEndPage() {
        return (startPage + displayPageNum) -1;
    }

    public String name(MemberVO memberVO) {
        return memberVO.getName();
    }

    public int getBoardId() {
        return boardId;
    }

    public String getFileId(FileVO fileVO) {
        return fileVO.getFileId();
    }

    public List<FileVO> getFileList(FileVO fileVO) {
        return getFileList();
    }
}
