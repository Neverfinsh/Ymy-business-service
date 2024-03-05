package com.script.ymy.service;

import com.script.ymy.dto.ThemDto;
import com.script.ymy.vo.req.AllthemReq;
import com.script.ymy.vo.req.ImporThemReq;
import com.script.ymy.vo.req.ThemVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

@Service
public interface ScriptService {

    List<ThemDto> findThemList(AllthemReq req);

    int updateScriptThem(ThemDto themDto);

    int delThem(Integer themId);

    ThemDto selectAllThem(AllthemReq allthemReq);

    ThemDto findThemListNoParams(AllthemReq allthemReq);

    ThemDto findThemListNo();

    ThemDto selectOneThem(AllthemReq allthemReq);

    int findThemById(String themId);

    int addThem(ThemVo themVo);

    int addThemRandom(ThemVo themVo);

    int addThemBath(List<ThemVo> themVos);

    boolean importThemFile(MultipartFile file, String userId);

    boolean importThemFileWithParams(MultipartFile file,String uid, String deviceId, String articleSendTime, String articleNum,String channel) throws ParseException;

    boolean importThemAllFile(MultipartFile file);

}
