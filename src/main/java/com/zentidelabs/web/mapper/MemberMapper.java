package com.zentidelabs.web.mapper;

import com.zentidelabs.web.model.MemberVO;
import org.springframework.stereotype.Repository;

@Repository("MemberMapper")
public interface MemberMapper {
    MemberVO getMemberInfo(MemberVO memberVO);

}
