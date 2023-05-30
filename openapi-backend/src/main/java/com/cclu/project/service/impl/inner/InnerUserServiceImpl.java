package com.cclu.project.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cclu.openapicommon.commons.ErrorCode;
import com.cclu.openapicommon.exception.BusinessException;
import com.cclu.openapicommon.model.entity.User;
import com.cclu.openapicommon.service.InnerUserService;
import com.cclu.project.service.UserService;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 * @author ChangCheng Lu
 * @date 2023/5/9 18:51
 */
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    private UserService userService;

    @Override
    public User getInvokeUser(String accessKey) {
        if (StringUtils.isBlank(accessKey)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accessKey", accessKey);

        User invokeUser = userService.getOne(queryWrapper);

        if (invokeUser == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        return invokeUser;
    }
}
