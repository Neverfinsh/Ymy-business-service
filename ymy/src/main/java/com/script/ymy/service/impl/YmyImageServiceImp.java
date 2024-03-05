package com.script.ymy.service.impl;

import com.script.ymy.dto.YmyArticleImgRel;
import com.script.ymy.dto.YmyImage;
import com.script.ymy.mapper.YmyArticleImgRelMapper;
import com.script.ymy.mapper.YmyImageMapper;
import com.script.ymy.service.YmyImageService;
import com.script.ymy.vo.req.YmyImageUploadReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class YmyImageServiceImp  implements YmyImageService {

   @Value("${file.uploadUrl}")
   private String uploadUrl;

   @Autowired
   private  YmyImageMapper imageMapper;

   @Autowired
   private YmyArticleImgRelMapper imgRelMapper;

   @Override
   public boolean upLoadImgRel(MultipartFile[] files, String deviceId, String accountId, String groupCodeId,String articleId) {
      try {
         List<YmyImage> imgList=new ArrayList<YmyImage>();
         for(int i=0;i<files.length;i++){
            MultipartFile file=files[i];
            String fileName = file.getOriginalFilename();
            File fileParent = new File( uploadUrl);
            if (!fileParent.exists()) {
               fileParent.mkdirs();
            }
            File newFile = new File(uploadUrl, fileName);
            log.info("-------------uploadUrl:{}--------",uploadUrl);
            log.info("-------------fileName:{}--------",fileName);
            if (!newFile.exists()) {
               newFile.createNewFile();
            }
            //  上传文件
            file.transferTo(newFile);
            //  组装参数
            String ip = InetAddress.getLocalHost().getHostAddress();
            log.info("-------------ip:{}--------",ip);
            String relativePath =  fileName;
            String absolutePath = "http://" + "101.201.33.155" + uploadUrl + relativePath;
            log.info("-------------absolutePath:{}--------",absolutePath);
            YmyImage image=new YmyImage();
            image.setDeviceId(deviceId);
            image.setName(fileName);
            image.setAbsolutelyPath(absolutePath);
            image.setAccountId(accountId);
            image.setRelativePath(relativePath);
            image.setGroupCodeId(groupCodeId);
            imgList.add(image);
            imageMapper.insert(image);
            //  插入 Rel
            Integer imageId=image.getId();
            YmyArticleImgRel imgRel=new YmyArticleImgRel();
            imgRel.setImagePath(absolutePath);
            imgRel.setImageId(imageId+"");
            imgRel.setArticleId(articleId);
            imgRelMapper.insert(imgRel);

         }
         return  true;
      } catch (IOException e) {
         e.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean upLoadImg( MultipartFile[]  files, String  deviceId, String  accountId, String  groupCodeId) {
      try {
         List<YmyImage> imgList=new ArrayList<YmyImage>();
         for(int i=0;i<files.length;i++){
            // 获取上传的文件名
            MultipartFile file=files[i];
            String fileName = file.getOriginalFilename();
            File fileParent = new File( uploadUrl);
            if (!fileParent.exists()) {
                 fileParent.mkdirs();
            }
            //  在目标文件新新建一个的路径
            File newFile = new File(uploadUrl, fileName);
            log.info("-------------uploadUrl:{}--------",uploadUrl);
            log.info("-------------fileName:{}--------",fileName);
            //  判断文件夹是否存在若不存在则逐级创建
            if (!newFile.exists()) {
                  newFile.createNewFile();
            }
            //  上传文件
            file.transferTo(newFile);
            //  组装参数
            String ip = InetAddress.getLocalHost().getHostAddress();
            log.info("-------------ip:{}--------",ip);
            String relativePath =  fileName;
            String absolutePath = "http://" + "101.201.33.155" + uploadUrl + relativePath;
            log.info("-------------absolutePath:{}--------",absolutePath);
            YmyImage image=new YmyImage();
            image.setDeviceId(deviceId);
            image.setName(fileName);
            image.setAbsolutelyPath(absolutePath);
            image.setAccountId(accountId);
            image.setRelativePath(relativePath);
            image.setGroupCodeId(groupCodeId);
            imgList.add(image);
         }
         // 批量插入数据库
         imageMapper.insertBathImg(imgList);
         return  true;
      } catch (IOException e) {
         e.printStackTrace();
         return false;
      }

   }

   @Override
   public List<YmyImage> imgList(YmyImageUploadReq imgListReq) {
      List<YmyImage>  list =imageMapper.findImgList(imgListReq);
      return list;
   }

   @Override
   public int delImg(String id) {
      return imageMapper.deleteByPrimaryKey(Integer.parseInt(id));
   }

   @Override
   public int delRelImg(String pkId) {
      return  imgRelMapper.deleteByPrimaryKey(Integer.parseInt(pkId));
   }
}
