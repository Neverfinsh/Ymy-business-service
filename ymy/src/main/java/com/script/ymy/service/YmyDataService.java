package com.script.ymy.service;

import com.script.ymy.dto.YmyData;
import com.script.ymy.vo.req.SelectDataListReq;
import com.script.ymy.vo.req.WebLoginReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface YmyDataService {

    List<YmyData> listRecord(SelectDataListReq dataListReq);

    YmyData   detailRecord(String  pkId);

}
