package com.cclu.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cclu.openapicommon.model.entity.InterfaceInfo;

/**
* @author 21237
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2023-04-28 10:56:30
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    /**
     * 校验
     *
     * @param interfaceInfo
     * @param add 是否为创建校验
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);


}
