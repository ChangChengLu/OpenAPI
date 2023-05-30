package com.cclu.project.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cclu.openapicommon.commons.ErrorCode;
import com.cclu.openapicommon.exception.BusinessException;
import com.cclu.openapicommon.model.entity.InterfaceInfo;
import com.cclu.openapicommon.service.InnerInterfaceInfoService;
import com.cclu.project.service.InterfaceInfoService;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 * @author ChangCheng Lu
 * @date 2023/5/9 18:53
 */
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Override
    public InterfaceInfo getInterfaceInfo(String url, String method) {
        if (StringUtils.isAnyBlank(url, method)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url", url);
        queryWrapper.eq("method", method);

        InterfaceInfo invokeInterfaceInfo = interfaceInfoService.getOne(queryWrapper);
        if (invokeInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        return invokeInterfaceInfo;
    }
}
