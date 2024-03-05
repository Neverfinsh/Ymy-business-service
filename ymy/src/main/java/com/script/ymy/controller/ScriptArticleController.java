package com.script.ymy.controller;

import com.script.ymy.dto.ScriptArticle;
import com.script.ymy.dto.YmyArticleImgRel;
import com.script.ymy.service.ScriptArticleService;
import com.script.ymy.vo.req.SelectTaskListReq;
import com.script.ymy.vo.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/article/")
public class ScriptArticleController {
    
    @Autowired
    ScriptArticleService articleService;
    
    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/findArticleList")
    public Result<List<ScriptArticle>> findArticleList(@RequestBody SelectTaskListReq req){
        try {
            return Result.<List<ScriptArticle> >builder().code(Result.SUCCESS_CODE).res(articleService.findArticleList(req)).build();
        } catch (Exception e) {
            return Result.<List<ScriptArticle>>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping("/web/findArticleImgRelList/{articleId}")
    public Result<List<YmyArticleImgRel>> findImgRelList(@PathVariable("articleId") String articleId){
        try {
            return Result.<List<YmyArticleImgRel> >builder().code(Result.SUCCESS_CODE).res(articleService.findArticleImgRelList(articleId)).build();
        } catch (Exception e) {
            return Result.<List<YmyArticleImgRel>>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/addArticle")
    public Result<Integer> addArticle(@RequestBody ScriptArticle article){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(articleService.saveArticle(article)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/importArticleFile")
    public Result<Boolean> importBathArticle(@RequestBody @RequestParam("file") MultipartFile file,
                                                          @RequestParam("userId") String  userId){
        try {
            return Result.<Boolean >builder().code(Result.SUCCESS_CODE).res(articleService.importArticleFile(file,userId)).build();
        } catch (Exception e) {
            return Result.<Boolean>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/updateArticle")
    public Result<Integer> updateArticle(@RequestBody ScriptArticle  article){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(articleService.updateArticle(article)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    @CrossOrigin
    @ResponseBody
    @DeleteMapping("/web/delArticle/{articleId}")
    public Result<Integer> delArticle(@PathVariable("articleId") String  articleId){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(articleService.delArticle(Integer.parseInt(articleId))).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    @CrossOrigin
    @ResponseBody
    @PostMapping("web/import/shortArticle/file")
    public Result<Boolean> importThemTxt(@RequestParam("file") MultipartFile file,
                                         @RequestParam("uid") String  uid,
                                         @RequestParam("deviceId") String  deviceId,
                                         @RequestParam("articleSendTime") String  articleSendTime,
                                         @RequestParam("articleNum") String  articleNum,
                                         @RequestParam("channel") String  channel){
        try {
            Boolean result=articleService.importShortArticle(file,uid,deviceId,articleSendTime,articleNum,channel);
            if(result){
                return Result.<Boolean>builder().code(Result.SUCCESS_CODE).build();
            }else {
                return Result.<Boolean>builder().code(Result.FAILED_CODE).build();
            }
        } catch (Exception e) {
            return Result.<Boolean>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("web/import/origialArticle/file")
    public Result<Boolean> importOriginalTxt(@RequestParam("file") MultipartFile file,
                                         @RequestParam("uid") String  uid,
                                         @RequestParam("deviceId") String  deviceId,
                                         @RequestParam("articleSendTime") String  articleSendTime,
                                         @RequestParam("articleNum") String  articleNum,
                                         @RequestParam("channel") String  channel){
        try {
            Boolean result=articleService.importShortArticle(file,uid,deviceId,articleSendTime,articleNum,channel);
            if(result){
                return Result.<Boolean>builder().code(Result.SUCCESS_CODE).build();
            }else {
                return Result.<Boolean>builder().code(Result.FAILED_CODE).build();
            }
        } catch (Exception e) {
            return Result.<Boolean>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }





    @CrossOrigin
    @ResponseBody
    @PostMapping("/script/findArticleList")
    public Result<List<ScriptArticle>> findArticleByScript(@RequestBody SelectTaskListReq req){
        try {
            return Result.<List<ScriptArticle> >builder().code(Result.SUCCESS_CODE).res(articleService.findArticleList(req)).build();
        } catch (Exception e) {
            return Result.<List<ScriptArticle>>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    /**
     *  用于执行脚本，获取今天需要发送的文章的内容
     * @param deviceId
     * @return
     */
    @CrossOrigin
    @ResponseBody
    @GetMapping("/script/execute/findOneArticle/{deviceId}")
    public Result<ScriptArticle> findOneArticle( @PathVariable("deviceId") String  deviceId){
        try {
            return Result.<ScriptArticle>builder().code(Result.SUCCESS_CODE).res(articleService.findAOneArticle(deviceId)).build();
        } catch (Exception e) {
            return Result.<ScriptArticle>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/script/execute/updateArticle/{articleId}")
    public Result<Integer> updateArticleByScript(@PathVariable("articleId") String  articleId){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(articleService.updateArticleStatus(articleId)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

}
