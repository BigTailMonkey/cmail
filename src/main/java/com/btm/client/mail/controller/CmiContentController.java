package com.btm.client.mail.controller;

import com.btm.client.mail.common.RestResult;
import com.btm.client.mail.entity.dao.CmiUserMailInfo;
import com.btm.client.mail.entity.dto.CmiUserMailInfoDTO;
import com.btm.client.mail.service.mail.CmiUserMailInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 收件人
 *
 * @Author: BigTailMonkey
 * @Date: 2019/6/17 9:22
 * @Version: 1.0
 */

@RestController
@RequestMapping("content")
public class CmiContentController {

    @Autowired
    private CmiUserMailInfoService cmiUserMailInfoService;

    /**
     * 获得全部邮件收件人信息
     *
     * @return
     */
    @GetMapping("userInfo/getAll")
    public String getAllUserInfo(){
        List<CmiUserMailInfo> list = cmiUserMailInfoService.list();
        if (null != list) {
            return list.toString();
        }else {
            return null;
        }
    }

    /**
     * 保存一个邮件收件人
     *
     * @param cmiUserMailInfoDTO
     * @return
     */
    @PostMapping("userInfo/saveOne")
    public RestResult saveOneUserInfo(CmiUserMailInfoDTO cmiUserMailInfoDTO){
        if(cmiUserMailInfoService.save(CmiUserMailInfo.createFromDAO(cmiUserMailInfoDTO))){
            return RestResult.SUCCESS("保存成功。",null);
        }else{
            return RestResult.FAILURE("保存失败。",null);
        }
    }

    /**
     * 删除一个收件人信息
     *
     * @param cmiUserMailInfoId 要删除的收件人的ID
     * @return
     */
    @PostMapping("userInfo/removeOne")
    public RestResult removeUserInfo(String cmiUserMailInfoId){
        if (cmiUserMailInfoService.removeById(cmiUserMailInfoId)){
            return RestResult.SUCCESS("删除成功。",null);
        }else{
            return RestResult.FAILURE("删除失败。",null);
        }
    }

}
