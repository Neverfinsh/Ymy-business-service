package com.script.ymy.service;

import com.script.ymy.dto.YmyUser;
import com.script.ymy.vo.req.WebLoginReq;
import org.springframework.stereotype.Service;

@Service
public interface YmyUserService {

    YmyUser  findUser(String  userId);

    YmyUser login(WebLoginReq loginReq);

    int  addUer(YmyUser user);

    int  updateUer(YmyUser user);

}
