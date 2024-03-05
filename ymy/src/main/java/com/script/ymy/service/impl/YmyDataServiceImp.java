package com.script.ymy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.script.ymy.dto.ThemDto;
import com.script.ymy.dto.YmyData;
import com.script.ymy.dto.YmyDevice;
import com.script.ymy.mapper.ScriptArticleMapper;
import com.script.ymy.mapper.ThemDtoMapper;
import com.script.ymy.mapper.YmyDeviceMapper;
import com.script.ymy.service.YmyDataService;
import com.script.ymy.vo.req.AllthemReq;
import com.script.ymy.vo.req.SelectDataListReq;
import com.script.ymy.vo.req.SelectDataThemReq;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class YmyDataServiceImp implements YmyDataService {


    @Autowired
    private YmyDeviceMapper deviceMapper;

    @Autowired
    private ScriptArticleMapper articleMapper;

    @Autowired
    private ThemDtoMapper  themMapper;

    @Override
    public List<YmyData> listRecord(SelectDataListReq dataListReq) {
       log.info("====================统计数据的的请求参数:{}============================", JSONObject.toJSONString(dataListReq));
        String  account=dataListReq.getUid();
        String  devicedId =dataListReq.getDeviceId();
        List<YmyDevice>  devices= deviceMapper.selectByUserAccount(account);
        List<YmyData> datas=new ArrayList<>();



        if(!devicedId.equals("") && "all".equals(devicedId)){
            log.info("=====================  选择了所有的设备 ================");
            for(YmyDevice device:devices){
                YmyData itemData=new YmyData();
                // [已经完成]
                SelectDataListReq finishStatus=dataListReq;
                finishStatus.setDeviceId(device.getId()+"");
                finishStatus.setStatus(1);
                finishStatus.setDeviceId(device.getDviceName());
                Integer finishCount=  articleMapper.countArticle(finishStatus);
                log.info("=====================  选择了所有的设备 : 完成{}  ================",finishCount);
                itemData.setFinishedCount(finishCount);
                // [ 未完成 ]
                SelectDataListReq unfinishStatus=dataListReq;
                unfinishStatus.setStatus(0);
                Integer unfinishCount=  articleMapper.countArticle(unfinishStatus);
                log.info("=====================  选择了所有的设备 : 未完成{}  ================",unfinishCount);
                itemData.setUnfinishedCount(unfinishCount);

                // [ 主题统计数量 ]

                SelectDataThemReq req=new SelectDataThemReq();
                req.setDeviceId(device.getDviceName());
                req.setUserId(dataListReq.getUid());
                req.setStartTime(dataListReq.getStartTime());
                req.setEndTime(dataListReq.getEndTime());
                List<ThemDto> list =  themMapper.countThemList(req);
                int themCount=list.size();
                log.info("=====================  主题统计{}  ================",themCount);
                itemData.setThemTotalCount(themCount);
                // [ 组装参数]
                itemData.setDevicedId(device.getDviceName());
                itemData.setUid(dataListReq.getUid());
                itemData.setFinishedCount(finishCount);
                itemData.setUnfinishedCount(unfinishCount);
                itemData.setTotalCount(null);
                itemData.setChannel(dataListReq.getChannel());
                datas.add(itemData);
            }
        }else{
            // [  完成 ]
            log.info("=====================  选择了具体的设备 ================");
            SelectDataListReq finishStatus=dataListReq;
            finishStatus.setStatus(1);
            Integer finishCount=  articleMapper.countArticle(finishStatus);
            log.info("=====================  选择了具体的设备 : 完成{}  ================",finishCount);
            // [  未完成 ]
            SelectDataListReq unfinishStatus=dataListReq;
            unfinishStatus.setStatus(0);
            Integer unfinishCount=  articleMapper.countArticle(unfinishStatus);
            log.info("=====================  选择了具体的设备 : 未完成{}  ================",unfinishCount);



            //  [ 主题数量  ]
            SelectDataThemReq req=new SelectDataThemReq();
            req.setDeviceId(devicedId);
            req.setUserId(dataListReq.getUid());
            req.setStartTime(dataListReq.getStartTime());
            req.setEndTime(dataListReq.getEndTime());
            List<ThemDto> list =  themMapper.countThemList(req);
            int themCount=list.size();
            log.info("=====================  主题统计{}  ================",themCount);

            //  [ 组装参数 ]
            YmyData itemData=new YmyData();
            itemData.setChannel(dataListReq.getChannel());
            itemData.setUid(dataListReq.getUid());
            itemData.setDevicedId(dataListReq.getDeviceId());
            itemData.setFinishedCount(finishCount);
            itemData.setUnfinishedCount(unfinishCount);
            itemData.setThemTotalCount(themCount);
            datas.add(itemData);
        }
        return datas;
    }

    @Override
    public YmyData detailRecord(String pkId) {
        return null;
    }
}
