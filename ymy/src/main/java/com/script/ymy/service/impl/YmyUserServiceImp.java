package com.script.ymy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.script.ymy.dto.YmyUser;
import com.script.ymy.mapper.YmyUserMapper;
import com.script.ymy.service.YmyUserService;
import com.script.ymy.vo.req.WebLoginReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class YmyUserServiceImp implements YmyUserService {

    @Autowired
    private YmyUserMapper userMapper;

    @Override
    public YmyUser findUser(String userId) {
        log.info("=======查询用户详情,请求参数 userId:{}", JSONObject.toJSONString(userId));
        YmyUser ymyUser = userMapper.selectByPrimaryKey(Integer.parseInt(userId));
        return ymyUser;
    }

    @Override
    public YmyUser login(WebLoginReq loginReq) {
        log.info("=======查询用户,请求参数 :{}", JSONObject.toJSONString(loginReq));
        YmyUser ymyUser = userMapper. selectByLoignReq(loginReq);
        return ymyUser;
    }

    @Override
    public int addUer(YmyUser user) {
        log.info("=======新增用户,请求参数 :{}", JSONObject.toJSONString(user));
       int result= userMapper.insert(user);
        return result;
    }

    @Override
    public int updateUer(YmyUser user) {
        log.info("=======更新用户,请求参数 :{}", JSONObject.toJSONString(user));
      int upateRes =  userMapper.updateByPrimaryKeySelective(user);
        return upateRes;
    }
}
