package com.zentidelabs.web.controller.customer.board;

import com.zentidelabs.web.model.BoardVO;
import com.zentidelabs.web.model.FileVO;
import com.zentidelabs.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/board", method ={RequestMethod.POST})
@RequiredArgsConstructor
public class BoardController {

    private final UserService service;

    @GetMapping("")
    public String board(BoardVO boardVO, Model model){

        ArrayList<BoardVO> resultList = service.getSearchList(boardVO);

        boardVO.setTotalCount(service.totalCount(boardVO));

        model.addAttribute("resultList", resultList);
        model.addAttribute("totalpage",boardVO.getTotalPage());
        model.addAttribute("boardVO",boardVO);
        model.addAttribute("startPage",boardVO.getStartPage());
        model.addAttribute("endPage",boardVO.getEndPage());

        return "board";
    }


    @GetMapping("/write")
    public String write(){

        return "write";
    }

    @PostMapping("/insert")
    public String insert(BoardVO boardVO, FileVO fileVO, @RequestPart MultipartFile files) throws IOException {

        service.boardInsert(boardVO);
        service.fileInsert(boardVO,fileVO,files);
        return "redirect:/board";
    }


    @GetMapping("/detail")
    public String detail(BoardVO boardVO, Model model){

        BoardVO detail = service.getDetail(boardVO);
        BoardVO next = service.getNext(boardVO);
        BoardVO prev = service.getPrev(boardVO);


        model.addAttribute("detail", detail);
        model.addAttribute("boardVO",boardVO);
        model.addAttribute("next",next);
        model.addAttribute("prev",prev);
        return "detail";
    }


    @GetMapping("/update")
    public String update(BoardVO boardVO, Model model){
        BoardVO detail = service.getDetail(boardVO);

        model.addAttribute("detail",detail);

        return "update";
    }


    @GetMapping("/delete")
    public String delete(BoardVO boardVO){
        service.delete(boardVO);
        return "redirect:/board";
    }

}

