package com.script.ymy.controller;

import com.script.ymy.dto.YmyImage;
import com.script.ymy.dto.YmyUser;
import com.script.ymy.service.YmyUserService;
import com.script.ymy.service.impl.YmyImageServiceImp;
import com.script.ymy.vo.req.WebLoginReq;
import com.script.ymy.vo.req.YmyImageUploadReq;
import com.script.ymy.vo.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/image/")
public class YmyImageController {

    @Autowired
    private YmyImageServiceImp imageServiceImp;

    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/uploadImg")
    public Result<Boolean> uploadImg(@RequestParam("files")  MultipartFile[]  files,
                                     @RequestParam("deviceId") String      deviceId,
                                     @RequestParam("accountId") String     accountId,
                                     @RequestParam("groupCodeId") String  groupCodeId){
        try {
            return Result.<Boolean >builder().code(Result.SUCCESS_CODE).res(imageServiceImp.upLoadImg(files,deviceId,accountId,groupCodeId)).build();
        } catch (Exception e) {
            return Result.<Boolean>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/uploadImgRel")
    public Result<Boolean> uploadImgAndRel(@RequestParam("files")  MultipartFile[]   files,
                                           @RequestParam("deviceId") String       deviceId,
                                           @RequestParam("accountId") String     accountId,
                                           @RequestParam("groupCodeId") String  groupCodeId,
                                           @RequestParam("articleId") String     articleId){
        try {
            return Result.<Boolean >builder().code(Result.SUCCESS_CODE).res(imageServiceImp.upLoadImgRel(files,deviceId,accountId,groupCodeId,articleId)).build();
        } catch (Exception e) {
            return Result.<Boolean>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/imageList")
    public Result<List<YmyImage>> imageList(@RequestBody YmyImageUploadReq  imgListReq){
        try {
            return Result.<List<YmyImage>  >builder().code(Result.SUCCESS_CODE).res(imageServiceImp.imgList(imgListReq)).build();
        } catch (Exception e) {
            return Result.<List<YmyImage> >builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    @CrossOrigin
    @ResponseBody
    @DeleteMapping("/web/delImg/{imgId}")
    public Result<Integer> delImg(@PathVariable("imgId") String   imgId){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(imageServiceImp.delImg(imgId)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    @CrossOrigin
    @ResponseBody
    @DeleteMapping("/web/delRelImg/{imgRelId}")
    public Result<Integer> delRelImg(@PathVariable("imgRelId") String   imgRelId){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(imageServiceImp.delRelImg(imgRelId)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


}
