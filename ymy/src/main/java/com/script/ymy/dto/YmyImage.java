package com.script.ymy.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class YmyImage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String accountId;

    private String deviceId;

    private String groupCodeId;

    private String relativePath;

    private String absolutelyPath;

}