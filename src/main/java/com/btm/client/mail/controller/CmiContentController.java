package com.btm.client.mail.controller;

import com.btm.client.mail.entity.CmiContent;
import com.btm.client.mail.service.mail.CmiContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/17 9:22
 * @Version: 1.0
 */

@RestController
@RequestMapping("content")
public class CmiContentController {

    @Autowired
    private CmiContentService cmiContentService;

//    @Autowired
//    private BaseConfig baseConfig;

    /**
     * 获得全部邮件信息
     *
     * @return
     */
    @GetMapping("all")
    public String getAllMailContent(){
        List<CmiContent> list = cmiContentService.list();
        if (null != list && 0<list.size()){
            return list.toString();
        }
        return "Get all mail content.";
    }

}
