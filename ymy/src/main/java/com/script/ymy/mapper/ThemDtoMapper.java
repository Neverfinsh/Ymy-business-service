package com.script.ymy.mapper;

import com.script.ymy.dto.ThemDto;
import com.script.ymy.vo.req.AllthemReq;
import com.script.ymy.vo.req.SelectDataThemReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ThemDtoMapper {

    List<ThemDto> findThemList(AllthemReq req);

    List<ThemDto> countThemList(SelectDataThemReq req);

     ThemDto findThemListNoParams(AllthemReq allthemReq);

     ThemDto findThemListWithoutParams();

    int delThem(Integer themId);

    int updateThem(ThemDto them);

    int insert(ThemDto record);

    ThemDto findAllThem(AllthemReq allthemReq);

    ThemDto selectOneThem( AllthemReq allthemReq);

    int updateThemStatus(String themId);

    int insertBatch(List<ThemDto> themDtos);

}