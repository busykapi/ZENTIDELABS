package com.zentidelabs.web.mapper;

import com.zentidelabs.web.model.FileVO;
import org.springframework.stereotype.Repository;

@Repository("FileMapper")
public interface FileMapper {
    int fileInsert (FileVO fileVO);

}
