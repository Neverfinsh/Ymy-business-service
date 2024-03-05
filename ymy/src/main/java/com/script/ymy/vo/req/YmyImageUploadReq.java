package com.script.ymy.vo.req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class YmyImageUploadReq  {

    private String deviceId;

    private String accountId;

    private String groupCodeId;

}