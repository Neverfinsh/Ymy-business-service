package com.script.ymy.mapper;

import com.script.ymy.dto.YmyImage;
import com.script.ymy.vo.req.YmyImageUploadReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface YmyImageMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(YmyImage record);

    int insertBathImg(List<YmyImage> images);

    int insertSelective(YmyImage record);

    YmyImage selectByPrimaryKey(Integer id);

    List<YmyImage> findImgList(YmyImageUploadReq req);

    int updateByPrimaryKeySelective(YmyImage record);

    int updateByPrimaryKey(YmyImage record);
}