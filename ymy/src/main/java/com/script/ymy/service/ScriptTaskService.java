package com.script.ymy.service;

import com.script.ymy.dto.ScriptTask;
import com.script.ymy.vo.req.AllthemReq;
import com.script.ymy.vo.req.SaveTaskReq;
import com.script.ymy.vo.req.SelectTaskListReq;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ScriptTaskService {

    List<ScriptTask> findTaskList(SelectTaskListReq req);

    int updateScriptTask(ScriptTask scriptTask);

    int saveTask(SaveTaskReq scriptTask);

    int  saveTaskBath(List<ScriptTask> list);

    ScriptTask selectOneTask(AllthemReq allthemReq);

    boolean  importThemFile(MultipartFile file, String  userId);

    boolean  importTaskAllUser(MultipartFile file);

    int delTask(Integer pkId);

}
