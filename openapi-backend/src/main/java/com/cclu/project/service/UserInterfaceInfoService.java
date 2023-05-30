package com.cclu.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cclu.openapicommon.model.entity.UserInterfaceInfo;

/**
* @author 21237
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2023-05-07 08:49:36
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {


    /**
     * 校验
     *
     * @param userInterfaceInfo
     * @param add 是否为创建校验
     */
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);


    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);


}
