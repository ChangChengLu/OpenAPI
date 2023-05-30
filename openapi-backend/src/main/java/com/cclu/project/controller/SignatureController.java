package com.cclu.project.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cclu.openapicommon.commons.BaseResponse;
import com.cclu.openapicommon.commons.ErrorCode;
import com.cclu.openapicommon.exception.BusinessException;
import com.cclu.openapicommon.model.entity.User;
import com.cclu.openapicommon.utils.ResultUtils;
import com.cclu.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.cclu.openapicommon.constant.UserConstant.SALT;


/**
 * @author ChangCheng Lu
 * @date 2023/5/17 10:46
 */
@RestController
@RequestMapping("/signature")
@Slf4j
public class SignatureController {

    @Resource
    private UserService userService;


    @PostMapping("/apply_accessKey")
    public BaseResponse<String> applyAccessKey(HttpServletRequest request) {
        // 1. 获取用户信息
        User loginUser = userService.getLoginUser(request);
        Long userId = loginUser.getId();
        String userAccount = loginUser.getUserAccount();
        // 3. 分配 accessKey / secretKey
        String accessKey = DigestUtil.md5Hex(SALT + userAccount + RandomUtil.randomNumbers(5));
        String secretKey = DigestUtil.md5Hex(SALT + userAccount + RandomUtil.randomNumbers(8));
        // 4. 插入数据
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", userId);
        updateWrapper.set("accessKey", accessKey);
        updateWrapper.set("secretKey", secretKey);

        boolean update = userService.update(updateWrapper);

        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "申请失败");
        }

        return ResultUtils.success(accessKey);
    }


    @PostMapping("/change_accessKey")
    public BaseResponse<String> changeAccessKey(HttpServletRequest request) {
        // 1. 获取用户信息
        User loginUser = userService.getLoginUser(request);
        Long userId = loginUser.getId();
        String userAccount = loginUser.getUserAccount();
        // 2. 生成 accessKey
        String accessKey = DigestUtil.md5Hex(SALT + userAccount + RandomUtil.randomNumbers(5));
        // 3. 更新
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", userId);
        updateWrapper.set("accessKey", accessKey);

        boolean update = userService.update(updateWrapper);

        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "申请失败");
        }

        return ResultUtils.success(accessKey);
    }
}
