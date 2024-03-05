package com.script.ymy.service;

import com.script.ymy.dto.ScriptArticle;
import com.script.ymy.dto.ScriptTask;
import com.script.ymy.dto.YmyArticleImgRel;
import com.script.ymy.vo.req.AllthemReq;
import com.script.ymy.vo.req.SaveTaskReq;
import com.script.ymy.vo.req.SelectTaskListReq;
import com.script.ymy.vo.req.YmyImageUploadReq;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

@Service
public interface ScriptArticleService {

    List<YmyArticleImgRel> findArticleImgRelList(String articleId);

    int   saveArticle(ScriptArticle article);

    int  saveArticleBath(List<ScriptArticle> list);

    boolean  importArticleFile(MultipartFile file, String userId);

    int delArticle(Integer pkId);

    int updateArticle(ScriptArticle scriptTask);

    int updateArticleStatus(String pkid);

    List<ScriptArticle> findArticleList(SelectTaskListReq req);

    ScriptArticle  findAOneArticle(String  userIde);

    boolean importShortArticle(MultipartFile file,String uid, String deviceId, String articleSendTime, String articleNum,String channel);

    boolean importOriginalArticle(MultipartFile file,String uid, String deviceId, String articleSendTime, String articleNum,String channel);

}
