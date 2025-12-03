package com.zentidelabs.web.service;

import com.zentidelabs.web.mapper.BoardMapper;
import com.zentidelabs.web.mapper.FileMapper;
import com.zentidelabs.web.mapper.LoginMapper;
import com.zentidelabs.web.mapper.MemberMapper;
import com.zentidelabs.web.model.*;
import com.zentidelabs.web.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {  //기능을하는곳
    private final BoardMapper mapper;
    private final MemberMapper memberMapper;
    private final FileMapper fileMapper;
    private final LoginMapper loginMapper;

    public ArrayList<BoardVO> getSearchList(BoardVO boardVO){
        return mapper.getSearchList(boardVO);
    }

    public ArrayList<CommentVO> getCommentList(){
        return mapper.getCommentList();
    }

    public ArrayList<FileVO> getFileList(BoardVO boardVO){return  mapper.getFileList(boardVO);}

    public int totalCount(BoardVO boardVO){
        return mapper.totalCount(boardVO);
    }

    public BoardVO getDetail(BoardVO boardVO) {
        BoardVO result = mapper.getDetail(boardVO);
        result.setFileList(mapper.getFileList(result));
        return result;
    }


    public MemberVO getMemberInfo(MemberVO memberVO){
        return memberMapper.getMemberInfo(memberVO);
    }

    public ArrayList<PackageVO> getPackageList(){
        return mapper.getPackageList();
    }

    public ArrayList<ReserveVO> getReserveList(){
        return mapper.getReserveList();
    }

    public int fileInsert (BoardVO boardVO, FileVO fileVO, @RequestPart MultipartFile files) throws IOException {

        String fileName = files.getOriginalFilename();
        String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
        int size = (int) files.getSize();
        File destinationFile;
        String destinationFileName;

        do {
            destinationFileName = RandomStringUtils.randomAlphanumeric(32)+"."+fileNameExtension;
            destinationFile = new File(fileVO.getPath() + destinationFileName);
        } while (destinationFile.exists());

        destinationFile.getParentFile().mkdirs();
        files.transferTo(destinationFile);

        fileVO.setFileId(RandomUtil.randomId()); // 18자리 -> aaa202212310142141
        fileVO.setFileOrder(1);
        fileVO.setFileName(destinationFileName);
        fileVO.setFileOriginName(fileName);
        fileVO.setSize(size);
        fileVO.setRegdate(boardVO.getRegdate());

        return fileMapper.fileInsert(fileVO);
    }


    public void boardInsert (BoardVO boardVO){
        mapper.boardInsert(boardVO);
    }


    public BoardVO getNext(BoardVO boardVO){
        return mapper.getNext(boardVO);
    }

    public BoardVO getPrev(BoardVO boardVO){
        return mapper.getPrev(boardVO);
    }

    public void update(BoardVO boardVO){
        mapper.update(boardVO);
    }

    public int delete(BoardVO boardVO){
       return mapper.delete(boardVO);
    }

    public MemberVO getLogin(MemberVO memberVO) {
        return loginMapper.getLogin(memberVO);
    }
}
