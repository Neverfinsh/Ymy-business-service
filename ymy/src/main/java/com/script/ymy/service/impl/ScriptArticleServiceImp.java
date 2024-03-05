package com.script.ymy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.script.ymy.dto.*;
import com.script.ymy.mapper.ScriptArticleMapper;
import com.script.ymy.mapper.YmyArticleImgRelMapper;
import com.script.ymy.mapper.YmyImageMapper;
import com.script.ymy.service.ScriptArticleService;
import com.script.ymy.vo.req.SelectTaskListReq;
import com.script.ymy.vo.req.YmyImageUploadReq;
import com.sun.javafx.text.ScriptMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class ScriptArticleServiceImp implements ScriptArticleService {

    @Autowired
     private ScriptArticleMapper articleMapper;

    @Autowired
     private  YmyImageMapper imageMapper;

    @Autowired
    private YmyArticleImgRelMapper articleImgRelMapper;


    @Override
    public List<YmyArticleImgRel> findArticleImgRelList(String articleId) {
        log.info("批量插入短文，获取的图片请求参数:{}",JSONObject.toJSONString(articleId));
        List<YmyArticleImgRel>  imgRelList= articleImgRelMapper.findImgRelList(articleId);
        log.info("批量插入短文，获取的图片返回参数:{}",JSONObject.toJSONString(imgRelList));
        return imgRelList;
    }

    @Override
    public int saveArticle(ScriptArticle article) {
        log.info("新增一条article的记录:{}",article);
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        int addArticleResult= articleMapper.insert(article);
        log.info("新增一条article的记录的结果:{}",addArticleResult);
        return addArticleResult;
    }

    @Override
    public int saveArticleBath(List<ScriptArticle> list) {
        log.info("批量新增article的记录:{}", JSONObject.toJSONString(list));
        int addArticleResult= articleMapper.insertBath(list);
        log.info("批量新增article的记录的结果:{}",addArticleResult);
        return 0;
    }

    @Override
    public boolean importArticleFile(MultipartFile file, String userId) {
        log.info("批量导入新增article的记录:{}", JSONObject.toJSONString(userId));
        if (file.isEmpty()) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        List<ScriptArticle> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            int counter = 1;
            while ((line = reader.readLine()) != null) {
                String task = line;
                ScriptArticle article = new ScriptArticle();
                calendar.add(Calendar.MINUTE, counter);
                Date nextDate = calendar.getTime();
                article.setUid(userId);
                article.setDeviceId(userId);
                article.setArticleSendTime(nextDate);
                article.setArticleThem(task);
                article.setCreateTime(new Date());
                article.setArticleNum(1);
                article.setStatus(0);
                list.add(article);
                counter++;
            }
            articleMapper.insertBath(list);
            reader.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int delArticle(Integer pkId) {
        log.info("删除一条article的记录:请求参数{}",pkId);
       int delResult= articleMapper.deleteByPrimaryKey(pkId);
        log.info("删除一条article的记录:返回后参数{}",delResult);
        return delResult;
    }

    @Override
    public int updateArticle(ScriptArticle article) {
        log.info("更新一条article的记录:请求参数{}",JSONObject.toJSONString(article));
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        log.info("立即发布当前的值:{}",new Date());
//        calendar.add(Calendar.MINUTE, 3);         // 加一分钟
//        Date oneMinuteLater = calendar.getTime();         // 获取加一分钟后的时间
//       log.info("立即发布更新后的值:{}",oneMinuteLater);
//        article.setUpdateTime(new Date());
//        article.setArticleSendTime(oneMinuteLater);
        int updateResult= articleMapper.updateByPrimaryKey(article);
        log.info("更新一条article的记录:返回参数{}",JSONObject.toJSONString(article));
        return updateResult;
    }

    @Override
    public int updateArticleStatus(String  pkid) {
        log.info("终端更新一条article的记录:请求参数{}",JSONObject.toJSONString(pkid));
        int updateResult= articleMapper.updateStatusByPrimaryKey(Integer.parseInt(pkid));
        log.info("终端更新一条article的记录:返回参数{}",JSONObject.toJSONString(updateResult));
        return updateResult;
    }

    @Override
    public List<ScriptArticle> findArticleList(SelectTaskListReq req) {
        log.info("查询article列表记录:请求参数{}",JSONObject.toJSONString(req));
        List<ScriptArticle>  list=  articleMapper.selectList(req);
        log.info("查询article列表的记录:返回参数{}",JSONObject.toJSONString(list));
        return list;
    }

    @Override
    public ScriptArticle findAOneArticle(String deviceId) {
        ScriptArticle  article=  articleMapper.selectByDeviceId(deviceId);
        List<YmyArticleImgRel>  rels= articleImgRelMapper.findImgRelList(article.getId()+"");
        List<String> imgRel=new ArrayList<>();
        for (YmyArticleImgRel rel: rels) {
             imgRel.add(rel.getImagePath());
        }
        article.setImgList(imgRel);
        return article;
    }


    // 批量导入短文
    @Transactional
    @Override
    public boolean importShortArticle(MultipartFile file,String uid, String deviceId, String articleSendTime, String articleNum,String channel) {
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
            calendar.setTime(date);
            List<ScriptArticle> list = new ArrayList<>();
            for(int i=0;i<userList.size();i++){
                String userId=userList.get(i);
                for (int j=0;j<themNameList.size();j++){
                    ScriptArticle shortArticle = new ScriptArticle();
                    String themName=themNameList.get(j);
                    // 定义正则表达式，匹配标点符号
                    String regex = "[，。！？]";
                    // 创建 Pattern 对象
                    Pattern pattern = Pattern.compile(regex);
                    // 创建 Matcher 对象
                    Matcher matcher = pattern.matcher(themName);
                    // 使用替换的方式在标点符号处添加换行符
                    String newThemName = matcher.replaceAll("$0\n\n").replaceAll("\\p{P}", "");;
                    shortArticle.setArticleThem(newThemName);
                    calendar.add(Calendar.MINUTE, 20);
                    Date nextDate = calendar.getTime();
                    log.info("===========nextDate==========:{}",nextDate);
                    //themDto.setArticleSendTime(nextDate);
                    shortArticle.setArticleSendTime(nextDate);
                    shortArticle.setArticleContent(newThemName);
                    shortArticle.setArticleTitle(newThemName);
                    shortArticle.setUid(userId);
                    shortArticle.setDeviceId(deviceId);
                    shortArticle.setCreateTime(new Date());
                    shortArticle.setArticleNum(Integer.parseInt(articleNum));
                    shortArticle.setStatus(0);
                    list.add(shortArticle);
                }
            }
            log.info("批量插入短文的结果集list:{}",JSONObject.toJSONString(list));
            // 从图库里面查询图片的
            YmyImageUploadReq req=new YmyImageUploadReq();
            req.setAccountId(uid);
            req.setDeviceId(deviceId);
            req.setGroupCodeId(deviceId);
            log.info("批量插入短文，获取的图片请求参数:{}",JSONObject.toJSONString(req));
            List<YmyImage>  imgList= imageMapper.findImgList(req);
            log.info("批量插入短文，获取的图片返回参数:{}",JSONObject.toJSONString(imgList));
            for(int i=0;i<list.size();i++){
                ScriptArticle article= list.get(i);
                articleMapper.insert(article);
                Integer articleId=article.getId();
                YmyArticleImgRel articleImgRel=new YmyArticleImgRel();

                Random random = new Random();
                int min = 0;
                int max = imgList.size();
                int randomNumber = random.nextInt(max - min + 1) + min;
                log.info("随机整数为:{}",randomNumber);
                YmyImage img=imgList.get(randomNumber);
                articleImgRel.setImageId(img.getId()+"");
                articleImgRel.setArticleId(String.valueOf(articleId));
                articleImgRel.setImagePath(img.getAbsolutelyPath());
                articleImgRelMapper.insert(articleImgRel);
            }
            reader.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean importOriginalArticle(MultipartFile file, String uid, String deviceId, String articleSendTime, String articleNum, String channel) {
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
            calendar.setTime(date);
            List<ScriptArticle> list = new ArrayList<>();
            for(int i=0;i<userList.size();i++){
                String userId=userList.get(i);
                for (int j=0;j<themNameList.size();j++){
                    ScriptArticle shortArticle = new ScriptArticle();
                    String themName=themNameList.get(j);
//                    // 定义正则表达式，匹配标点符号
//                    String regex = "[，。！？]";
//                    // 创建 Pattern 对象
//                    Pattern pattern = Pattern.compile(regex);
//                    // 创建 Matcher 对象
//                    Matcher matcher = pattern.matcher(themName);
//                    // 使用替换的方式在标点符号处添加换行符
//                    String newThemName = matcher.replaceAll("$0\n\n").replaceAll("\\p{P}", "");;
                    shortArticle.setArticleThem(themName);
                    calendar.add(Calendar.MINUTE, 20);
                    Date nextDate = calendar.getTime();
                    log.info("===========nextDate==========:{}",nextDate);
                    //themDto.setArticleSendTime(nextDate);
                    shortArticle.setArticleSendTime(nextDate);
                    shortArticle.setArticleContent(themName);
                    shortArticle.setArticleTitle(themName);
                    shortArticle.setUid(userId);
                    shortArticle.setDeviceId(deviceId);
                    shortArticle.setCreateTime(new Date());
                    shortArticle.setArticleNum(Integer.parseInt(articleNum));
                    shortArticle.setStatus(0);
                    list.add(shortArticle);
                }
            }
            log.info("批量插入短文的结果集list:{}",JSONObject.toJSONString(list));
            // 从图库里面查询图片的
            YmyImageUploadReq req=new YmyImageUploadReq();
            req.setAccountId(uid);
            req.setDeviceId(deviceId);
            req.setGroupCodeId(deviceId);
            log.info("批量插入短文，获取的图片请求参数:{}",JSONObject.toJSONString(req));
            List<YmyImage>  imgList= imageMapper.findImgList(req);
            log.info("批量插入短文，获取的图片返回参数:{}",JSONObject.toJSONString(imgList));
            for(int i=0;i<list.size();i++){
                ScriptArticle article= list.get(i);
                articleMapper.insert(article);
                Integer articleId=article.getId();
                YmyArticleImgRel articleImgRel=new YmyArticleImgRel();

                Random random = new Random();
                int min = 0;
                int max = imgList.size();
                int randomNumber = random.nextInt(max - min + 1) + min;
                log.info("随机整数为:{}",randomNumber);
                YmyImage img=imgList.get(randomNumber);
                articleImgRel.setImageId(img.getId()+"");
                articleImgRel.setArticleId(String.valueOf(articleId));
                articleImgRel.setImagePath(img.getAbsolutelyPath());
                articleImgRelMapper.insert(articleImgRel);
            }
            reader.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}