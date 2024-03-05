package com.script.ymy.service;

import com.script.ymy.dto.YmyImage;
import com.script.ymy.vo.req.YmyImageUploadReq;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface YmyImageService {

    boolean  upLoadImgRel(MultipartFile[] files, String  deviceId, String  accountId, String  groupCodeId,String articleId);

   boolean  upLoadImg(MultipartFile[] files, String  deviceId, String  accountId, String  groupCodeId);

   List<YmyImage> imgList(YmyImageUploadReq imgListReq);

   int delImg(String id);

   int delRelImg(String pkId);
 }
