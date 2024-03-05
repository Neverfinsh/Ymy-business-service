package com.script.ymy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.script.ymy.dto.ThemDto;
import com.script.ymy.dto.YmyDevice;
import com.script.ymy.mapper.ThemDtoMapper;
import com.script.ymy.mapper.YmyDeviceMapper;
import com.script.ymy.service.ScriptService;
import com.script.ymy.vo.req.AllthemReq;
import com.script.ymy.vo.req.ImporThemReq;
import com.script.ymy.vo.req.ThemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class ScriptServiceImp implements ScriptService {

    @Autowired(required = true)
    private ThemDtoMapper themDtoMapper;


    @Autowired(required = true)
    private YmyDeviceMapper deviceMapper;

    @Override
    public List<ThemDto> findThemList(AllthemReq req) {
        log.info("=======查询列表them,请求参数:{}", JSONObject.toJSONString(req));
        List<ThemDto>  list= themDtoMapper.findThemList(req);
        return list;
    }

    @Override
    public int updateScriptThem(ThemDto themDto) {
        log.info("=======更新them,请求参数:{}", JSONObject.toJSONString(themDto));
        int updateResult=themDtoMapper.updateThem(themDto);
        return updateResult;
    }

    @Override
    public int delThem(Integer themId) {
        log.info("=======删除them,请求参数:{}", JSONObject.toJSONString(themId));
        int delResult= themDtoMapper.delThem(themId);
        return delResult;
    }

    @Override
    public ThemDto selectAllThem(AllthemReq req) {
        log.info("========查询所有的主题 ,执行方法 SelectAllThem 的请求参数:{}", JSONObject.toJSONString(req));
        ThemDto list = themDtoMapper.findAllThem(req);
        log.info("========查询所有的主题 ,执行方法 SelectAllThem 返回参数:{}", JSONObject.toJSONString(list));
        return list;
    }

    @Override
    public ThemDto findThemListNoParams(AllthemReq allthemReq) {
         ThemDto  themDto= themDtoMapper.findThemListNoParams(allthemReq);
        log.info("========查询所有的主题 ,执行方法 findThemListNoParams 返回参数:{}", JSONObject.toJSONString(themDto));
        return themDto;
    }

    @Override
    public ThemDto findThemListNo() {
        ThemDto  themDto= themDtoMapper.findThemListWithoutParams();
        log.info("========查询所有的主题 ,执行方法 findThemListNoParams 返回参数:{}", JSONObject.toJSONString(themDto));
        return themDto;
    }

    @Override
    public ThemDto selectOneThem(AllthemReq req) {
        ThemDto themDto = themDtoMapper.findAllThem(req);
        return themDto;
    }


    @Override
    public int findThemById(String themId) {
        return themDtoMapper.updateThemStatus(themId);
    }

    @Override
    public int addThem(ThemVo themVo) {
        String deviceFlag=themVo.getDeviceId();
        String account=themVo.getUid();
        if("all".equals(deviceFlag)){
            log.info("========新增时选择了【所有设备】查询时,account:{}=======",account);
            List<YmyDevice>  devices= deviceMapper.selectByUserAccount(account);
            log.info("========新增时选择了【所有设备】查询出来结果:{}=======",JSONObject.toJSONString(devices));
            for(YmyDevice device:devices){
                String deviceId=  device.getDviceName();  // 设备编号
                ThemDto themDto = new ThemDto();
                BeanUtils.copyProperties(themVo, themDto);
                themDto.setDeviceId(deviceId);
                themDtoMapper.insert(themDto);
            }
        }else {
            ThemDto themDto = new ThemDto();
            BeanUtils.copyProperties(themVo, themDto);
            return themDtoMapper.insert(themDto);
        }
        return 1;
    }

    /**
     * 同一个话题生成10个随机的
     *
     * @param themVo
     * @return
     */
    @Override
    public int addThemRandom(ThemVo themVo) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        List<ThemDto> list = new ArrayList<ThemDto>();
        for (int i = 1; i < 50; i++) {
            ThemDto themDto = new ThemDto();
            BeanUtils.copyProperties(themVo, themDto);
            calendar.add(Calendar.MINUTE, 5 + i);
            Date nextDate = calendar.getTime();
            themDto.setArticleSendTime(nextDate);
            list.add(themDto);
        }
        themDtoMapper.insertBatch(list);
        return 1;
    }


    @Override
    public int addThemBath(List<ThemVo> themVos) {
        List<ThemDto> list = new ArrayList<>();
        for (ThemVo themVo : themVos) {
            ThemDto themDto = new ThemDto();
            BeanUtils.copyProperties(themVo, themDto);
            themDto.setCreateTime(new Date());
            list.add(themDto);
        }
        themDtoMapper.insertBatch(list);
        return 0;
    }

    @Override
    public boolean importThemFile(MultipartFile file, String userId) {
        if (file.isEmpty()) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        List<ThemDto> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            int counter = 1;
            while ((line = reader.readLine()) != null) {
                String them = line;
                ThemDto themDto = new ThemDto();
                calendar.add(Calendar.MINUTE, counter);
                Date nextDate = calendar.getTime();
                themDto.setUid(userId);
                themDto.setDeviceId(userId);
                themDto.setArticleSendTime(nextDate);
                themDto.setArticleThem(them);
                themDto.setCreateTime(new Date());
                themDto.setArticleNum(1);
                themDto.setStatus(0);
                list.add(themDto);
                counter++;
            }
            themDtoMapper.insertBatch(list);
            reader.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean importThemFileWithParams(MultipartFile file,String uid, String deviceId, String articleSendTime, String articleNum,String channel) {

        if (file.isEmpty()) {
            return false;
        }
        List<String> userList = new ArrayList<>();
        userList.add(uid);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(articleSendTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<String> themNameList=new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                String themName = line;
                themNameList.add(themName);
            }

            Calendar calendar = Calendar.getInstance();
        //    calendar.setTime(new Date());
            calendar.setTime(date);

            List<ThemDto> list = new ArrayList<>();
            for(int i=0;i<userList.size();i++){
                String userId=userList.get(i);
                for (int j=0;j<themNameList.size();j++){

                    ThemDto themDto = new ThemDto();
                    String themName=themNameList.get(j);
                    themDto.setArticleThem(themName);

                    calendar.add(Calendar.HOUR, 1);
                    Date nextDate = calendar.getTime();
                    log.info("===========nextDate==========:{}",nextDate);
                    //themDto.setArticleSendTime(nextDate);
                    themDto.setArticleSendTime(nextDate);

                    themDto.setUid(userId);
                    themDto.setDeviceId(deviceId);
                    themDto.setCreateTime(new Date());
                    themDto.setArticleNum(Integer.parseInt(articleNum));
                    themDto.setStatus(0);
                    list.add(themDto);
                }
            }

            themDtoMapper.insertBatch(list);
            reader.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean importThemAllFile(MultipartFile file) {
        if (file.isEmpty()) {
            return false;
        }
        List<String> userList = new ArrayList<>();
        userList.add("001");
        userList.add("002");
        userList.add("003");

        List<String> themNameList=new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
                while ((line = reader.readLine()) != null) {
                    String themName = line;
                    themNameList.add(themName);
                }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());

            List<ThemDto> list = new ArrayList<>();
            for(int i=0;i<userList.size();i++){
                String userId=userList.get(i);
                for (int j=0;j<themNameList.size();j++){

                    ThemDto themDto = new ThemDto();
                    String themName=themNameList.get(j);
                    themDto.setArticleThem(themName);

                    calendar.add(Calendar.HOUR, j+2);
                    Date nextDate = calendar.getTime();
                    log.info("===========nextDate==========:{}",nextDate);
                    themDto.setArticleSendTime(nextDate);

                    themDto.setUid(userId);
                    themDto.setDeviceId(userId);
                    themDto.setCreateTime(new Date());
                    themDto.setArticleNum(5);
                    themDto.setStatus(0);
                    list.add(themDto);
                }
            }

            themDtoMapper.insertBatch(list);
            reader.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
}