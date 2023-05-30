package com.cclu.project.service.impl.inner;

import com.cclu.openapicommon.service.InnerUserInterfaceInfoService;
import com.cclu.project.service.UserInterfaceInfoService;

import javax.annotation.Resource;

/**
 * @author ChangCheng Lu
 * @date 2023/5/9 18:53
 */
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        return userInterfaceInfoService.invokeCount(interfaceInfoId, userId);
    }
}
