package com.script.ymy.mapper;

import com.script.ymy.dto.ScriptArticle;
import com.script.ymy.vo.req.SelectDataListReq;
import com.script.ymy.vo.req.SelectTaskListReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ScriptArticleMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ScriptArticle record);

    int insertBath(List<ScriptArticle> list);

    int insertSelective(ScriptArticle record);

    ScriptArticle selectByPrimaryKey(Integer id);

    ScriptArticle selectByDeviceId(String deviceId);

    List<ScriptArticle> selectList(SelectTaskListReq req);

    int updateByPrimaryKeySelective(ScriptArticle record);

    int updateByPrimaryKey(ScriptArticle record);

    int updateStatusByPrimaryKey(Integer recordId);

    int  countArticle(SelectDataListReq record);

}