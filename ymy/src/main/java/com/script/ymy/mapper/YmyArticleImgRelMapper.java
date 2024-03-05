package com.script.ymy.mapper;

import com.script.ymy.dto.YmyArticleImgRel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface YmyArticleImgRelMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(YmyArticleImgRel record);

    int insertSelective(YmyArticleImgRel record);

    YmyArticleImgRel selectByPrimaryKey(Integer id);

    List<YmyArticleImgRel> findImgRelList(String  articleId);

    int updateByPrimaryKeySelective(YmyArticleImgRel record);

    int updateByPrimaryKey(YmyArticleImgRel record);

}