package com.script.ymy.service.impl;

import com.script.ymy.dto.ScriptTask;
import com.script.ymy.mapper.ScriptTaskMapper;
import com.script.ymy.service.ScriptTaskService;
import com.script.ymy.vo.req.AllthemReq;
import com.script.ymy.vo.req.SaveTaskReq;
import com.script.ymy.vo.req.SelectTaskListReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ScriptTaskServiceImp implements ScriptTaskService {

    @Autowired(required=true)
    private ScriptTaskMapper scriptTaskMapper;

    @Override
    public List<ScriptTask> findTaskList(SelectTaskListReq req) {
        List<ScriptTask>  taskList=scriptTaskMapper.selectTaskList();
        return taskList;
    }

    @Override
    public int updateScriptTask(ScriptTask scriptTask) {
        int  updateResult=scriptTaskMapper.updateByPrimaryKeySelective(scriptTask);
        return updateResult;
    }

    @Override
    public int saveTask(SaveTaskReq req) {
        ScriptTask task=new ScriptTask();
        BeanUtils.copyProperties(req, task);
        int insertResult= scriptTaskMapper.insert(task);
        return insertResult;
    }

    @Override
    public int saveTaskBath(List<ScriptTask> list) {
         int  insertBathResult=scriptTaskMapper.insertBatch(list);
         return insertBathResult;
    }

    @Override
    public ScriptTask selectOneTask(AllthemReq req) {
        ScriptTask task=scriptTaskMapper.findOneTask(req);
        return task;
    }

    public boolean importThemFile(MultipartFile file, String userId) {
        if (file.isEmpty()) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        List<ScriptTask> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            int counter = 1;
            while ((line = reader.readLine()) != null) {
                String task = line;
                ScriptTask themDto = new ScriptTask();
                calendar.add(Calendar.MINUTE, counter);
                Date nextDate = calendar.getTime();
                themDto.setUid(userId);
                themDto.setDeviceId(userId);
                themDto.setArticleSendTime(nextDate);
                themDto.setArticleThem(task);
                themDto.setCreateTime(new Date());
                themDto.setArticleNum(1);
                themDto.setStatus(0);
                list.add(themDto);
                counter++;
            }
            scriptTaskMapper.insertBatch(list);
            reader.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean importTaskAllUser(MultipartFile file) {
        if (file.isEmpty()) {
            return false;
        }
        // 一个用户 一天的50条 
        List<String> userList=new ArrayList<>();
        userList.add("001");
        userList.add("002");
        userList.add("003");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        List<ScriptTask> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            int counter = 1;
            while ((line = reader.readLine()) != null) {
                String task = line;
                ScriptTask themDto = new ScriptTask();
                calendar.add(Calendar.MINUTE, counter);
                Date nextDate = calendar.getTime();
                themDto.setArticleSendTime(nextDate);
                themDto.setArticleThem(task);
                themDto.setCreateTime(new Date());
                themDto.setArticleNum(1);
                themDto.setStatus(0);
                list.add(themDto);
                counter++;
            }
            scriptTaskMapper.insertBatch(list);
            reader.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public int delTask(Integer pkId) {
        return  scriptTaskMapper.deleteByPrimaryKey(pkId);
    }


}