package com.zentidelabs.web.mapper;

import com.zentidelabs.web.model.MemberVO;
import org.springframework.stereotype.Repository;

@Repository("LoginMapper")
public interface LoginMapper {
    MemberVO getLogin(MemberVO memberVO);

}
