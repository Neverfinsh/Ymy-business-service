package com.script.ymy.mapper;

import com.script.ymy.dto.YmyUser;
import com.script.ymy.vo.req.WebLoginReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface YmyUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(YmyUser record);

    int insertSelective(YmyUser record);

    YmyUser selectByPrimaryKey(Integer id);

    YmyUser selectByLoignReq(WebLoginReq loginReq);

    int updateByPrimaryKeySelective(YmyUser record);

    int updateByPrimaryKey(YmyUser record);

}