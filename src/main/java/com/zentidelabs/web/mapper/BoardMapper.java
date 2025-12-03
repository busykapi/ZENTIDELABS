package com.zentidelabs.web.mapper;

import com.zentidelabs.web.model.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository("BoardMapper")
public interface BoardMapper {

    /*검색*/
    ArrayList<BoardVO> getSearchList(BoardVO boardVO);
    ArrayList<CommentVO> getCommentList();
    ArrayList<FileVO> getFileList(BoardVO boardVO);

    /*상세페이지*/
    BoardVO getDetail(BoardVO boardVO);


    ArrayList<PackageVO> getPackageList();
    ArrayList<ReserveVO> getReserveList();



    /*삽입*/
    void boardInsert(BoardVO boardVO);

    /*수정*/
    void update (BoardVO boardVO);

    /*삭제*/
    int delete(BoardVO boardVO);
    int deleteAll(int[] ids);


    /*현재페이지수*/
    int pageNum();

    /*페이징처리(전체페이지수)*/
    int totalCount(BoardVO boardVO);

    /*다음페이지,이전페이지*/
    BoardVO getNext(BoardVO boardVO);

    BoardVO getPrev(BoardVO boardVO);

    /*공개*/


    /*일괄공개*/
    int updatePubAll(int[] ids);

}