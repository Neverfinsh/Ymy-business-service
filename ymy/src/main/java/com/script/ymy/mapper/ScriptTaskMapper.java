package com.script.ymy.mapper;

import com.script.ymy.dto.ScriptTask;
import com.script.ymy.vo.req.AllthemReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScriptTaskMapper {

    List<ScriptTask> selectTaskList();

    ScriptTask findOneTask(AllthemReq allthemReq);

    int deleteByPrimaryKey(Integer id);

    int insert(ScriptTask record);

    int insertSelective(ScriptTask record);

    ScriptTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScriptTask record);

    int updateByPrimaryKey(ScriptTask record);

    int insertBatch(List<ScriptTask> themDtos);
}