package com.btm.client.mail.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.btm.client.mail.entity.dao.CmiUserMailInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/24 17:32
 * @Version: 1.0
 */
@Mapper
public interface CmiUserMailInfoMapper extends BaseMapper<CmiUserMailInfo> {

    /**
     * 使用USER_ID批量查询用户邮箱地址
     *
     * @param userIdArray
     * @return
     */
    @Select("<script>"
            + "select i.user_mail_address from cmi_user_mail_info i where i.user_id in "
            + "<foreach item='item' index='index' collection='userIdArray' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<CmiUserMailInfo> getUserInfoByUserIdBatch(@Param("userIdArray") String[] userIdArray);
}
