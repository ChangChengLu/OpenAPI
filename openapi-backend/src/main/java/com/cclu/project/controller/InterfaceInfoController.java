package com.cclu.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cclu.openapiclientsdk.client.OpenApiClient;
import com.cclu.openapicommon.commons.BaseResponse;
import com.cclu.openapicommon.commons.DeleteRequest;
import com.cclu.openapicommon.commons.ErrorCode;
import com.cclu.openapicommon.commons.IdRequest;
import com.cclu.openapicommon.constant.CommonConstant;
import com.cclu.openapicommon.exception.BusinessException;
import com.cclu.openapicommon.model.entity.InterfaceInfo;
import com.cclu.openapicommon.model.entity.User;
import com.cclu.openapicommon.model.enums.InterfaceInfoStatusEnums;
import com.cclu.openapicommon.utils.ResultUtils;
import com.cclu.project.annotation.AuthCheck;
import com.cclu.project.model.dto.interfaceinfo.InterfaceInfoAddRequest;
import com.cclu.project.model.dto.interfaceinfo.InterfaceInfoInvokeRequest;
import com.cclu.project.model.dto.interfaceinfo.InterfaceInfoQueryRequest;
import com.cclu.project.model.dto.interfaceinfo.InterfaceInfoUpdateRequest;
import com.cclu.project.service.InterfaceInfoService;
import com.cclu.project.service.UserService;
import com.google.gson.Gson;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 接口管理
 *
 * @author cclu
 */
@RestController
@RequestMapping("/interfaceInfo")
@Slf4j
public class InterfaceInfoController {

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建
     *
     * @param InterfaceInfoAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addInterfaceInfo(@RequestBody InterfaceInfoAddRequest InterfaceInfoAddRequest, HttpServletRequest request) {
        if (InterfaceInfoAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo InterfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(InterfaceInfoAddRequest, InterfaceInfo);
        // 校验
        interfaceInfoService.validInterfaceInfo(InterfaceInfo, true);
        User loginUser = userService.getLoginUser(request);
        InterfaceInfo.setUserId(loginUser.getId());
        boolean result = interfaceInfoService.save(InterfaceInfo);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        long newInterfaceInfoId = InterfaceInfo.getId();
        return ResultUtils.success(newInterfaceInfoId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteInterfaceInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可删除
        if (!oldInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = interfaceInfoService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新
     *
     * @param InterfaceInfoUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateInterfaceInfo(@RequestBody InterfaceInfoUpdateRequest InterfaceInfoUpdateRequest,
                                            HttpServletRequest request) {
        if (InterfaceInfoUpdateRequest == null || InterfaceInfoUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo InterfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(InterfaceInfoUpdateRequest, InterfaceInfo);
        // 参数校验
        interfaceInfoService.validInterfaceInfo(InterfaceInfo, false);
        User user = userService.getLoginUser(request);
        long id = InterfaceInfoUpdateRequest.getId();
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可修改
        if (!oldInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = interfaceInfoService.updateById(InterfaceInfo);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<InterfaceInfo> getInterfaceInfoById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo InterfaceInfo = interfaceInfoService.getById(id);
        return ResultUtils.success(InterfaceInfo);
    }

    /**
     * 获取列表（仅管理员可使用）
     *
     * @param InterfaceInfoQueryRequest
     * @return
     */
    @AuthCheck(mustRole = "admin")
    @GetMapping("/list")
    public BaseResponse<List<InterfaceInfo>> listInterfaceInfo(InterfaceInfoQueryRequest InterfaceInfoQueryRequest) {
        InterfaceInfo InterfaceInfoQuery = new InterfaceInfo();
        if (InterfaceInfoQueryRequest != null) {
            BeanUtils.copyProperties(InterfaceInfoQueryRequest, InterfaceInfoQuery);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>(InterfaceInfoQuery);
        List<InterfaceInfo> InterfaceInfoList = interfaceInfoService.list(queryWrapper);
        return ResultUtils.success(InterfaceInfoList);
    }

    /**
     * 分页获取列表
     *
     * @param InterfaceInfoQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<InterfaceInfo>> listInterfaceInfoByPage(InterfaceInfoQueryRequest InterfaceInfoQueryRequest, HttpServletRequest request) {
        if (InterfaceInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo InterfaceInfoQuery = new InterfaceInfo();
        BeanUtils.copyProperties(InterfaceInfoQueryRequest, InterfaceInfoQuery);
        long current = InterfaceInfoQueryRequest.getCurrent();
        long size = InterfaceInfoQueryRequest.getPageSize();
        String sortField = InterfaceInfoQueryRequest.getSortField();
        String sortOrder = InterfaceInfoQueryRequest.getSortOrder();
        String description = InterfaceInfoQuery.getDescription();
        // content 需支持模糊搜索
        InterfaceInfoQuery.setDescription(null);
        // 限制爬虫
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>(InterfaceInfoQuery);
        queryWrapper.like(StringUtils.isNotBlank(description), "description", description);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<InterfaceInfo> InterfaceInfoPage = interfaceInfoService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(InterfaceInfoPage);
    }

    // endregion


    @PostMapping("/online")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<Boolean> onlineInterfaceInfo(IdRequest idRequest) {
        if (idRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数错误");
        }

        Long id = idRequest.getId();
        // 检验id
        if (id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数错误");
        }

        InterfaceInfo interfaceInfo = interfaceInfoService.getById(id);

        // 校验查询结果
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "请求数据不存在");
        }

        // 校验查询数据是否被删除
        if (interfaceInfo.getIsDelete() == 1) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "请求数据不存在");
        }

        Integer status = interfaceInfo.getStatus();
        InterfaceInfoStatusEnums interfaceInfoStatusEnum = InterfaceInfoStatusEnums.getEnumByCode(status);
        // 检验接口是否已发布
        if (InterfaceInfoStatusEnums.ONLINE.equals(interfaceInfoStatusEnum)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口已发布");
        }

        // 校验接口是否被禁止
        if (InterfaceInfoStatusEnums.FORBID.equals(interfaceInfoStatusEnum)) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "接口禁止使用");
        }

        // 校验接口是否关闭
        if (InterfaceInfoStatusEnums.OFFLINE.equals(interfaceInfoStatusEnum)) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "接口已下线");
        }

        interfaceInfo.setId(id);
        interfaceInfo.setStatus(InterfaceInfoStatusEnums.ONLINE.getCode());

        boolean isUpdate = interfaceInfoService.updateById(interfaceInfo);
        if (!isUpdate) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        return ResultUtils.success(true);
    }

    @PostMapping("/offline")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<Boolean> offlineInterfaceInfo(IdRequest idRequest) {
        if (idRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数错误");
        }

        Long id = idRequest.getId();
        // 检验id
        if (id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数错误");
        }

        InterfaceInfo interfaceInfo = interfaceInfoService.getById(id);

        // 校验查询结果
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "请求数据不存在");
        }

        // 校验查询数据是否被删除
        if (interfaceInfo.getIsDelete() == 1) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "请求数据不存在");
        }

        Integer status = interfaceInfo.getStatus();
        InterfaceInfoStatusEnums interfaceInfoStatusEnum = InterfaceInfoStatusEnums.getEnumByCode(status);
        // 校验接口是否已下线
        if (InterfaceInfoStatusEnums.OFFLINE.equals(interfaceInfoStatusEnum)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口已下线");
        }
        // 校验接口是否被禁止
        if (InterfaceInfoStatusEnums.FORBID.equals(interfaceInfoStatusEnum)) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "接口禁止使用");
        }

        interfaceInfo.setId(id);
        interfaceInfo.setStatus(0);

        boolean isUpdate = interfaceInfoService.updateById(interfaceInfo);
        if (!isUpdate) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        return ResultUtils.success(true);
    }


    @PostMapping("/forbid")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<Boolean> forbidInterfaceInfo(IdRequest idRequest) {
        if (idRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数错误");
        }

        Long id = idRequest.getId();
        // 检验id
        if (id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数错误");
        }

        InterfaceInfo interfaceInfo = interfaceInfoService.getById(id);

        // 校验查询结果
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "请求数据不存在");
        }

        // 校验查询数据是否被删除
        if (interfaceInfo.getIsDelete() == 1) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "请求数据不存在");
        }

        Integer status = interfaceInfo.getStatus();
        InterfaceInfoStatusEnums interfaceInfoStatusEnum = InterfaceInfoStatusEnums.getEnumByCode(status);
        // 校验接口是否已禁用
        if (InterfaceInfoStatusEnums.FORBID.equals(interfaceInfoStatusEnum)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口已禁用");
        }

        interfaceInfo.setId(id);
        interfaceInfo.setStatus(3);

        boolean isUpdate = interfaceInfoService.updateById(interfaceInfo);
        if (!isUpdate) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        return ResultUtils.success(true);
    }

    @PostMapping("/permit")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<Boolean> permitInterfaceInfo(IdRequest idRequest) {
        if (idRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数错误");
        }

        Long id = idRequest.getId();
        // 检验id
        if (id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数错误");
        }

        InterfaceInfo interfaceInfo = interfaceInfoService.getById(id);

        // 校验查询结果
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "请求数据不存在");
        }

        // 校验查询数据是否被删除
        if (interfaceInfo.getIsDelete() == 1) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "请求数据不存在");
        }

        Integer status = interfaceInfo.getStatus();
        InterfaceInfoStatusEnums interfaceInfoStatusEnum = InterfaceInfoStatusEnums.getEnumByCode(status);
        // 校验接口是否已启用
        if (InterfaceInfoStatusEnums.OFFLINE.equals(interfaceInfoStatusEnum) || InterfaceInfoStatusEnums.ONLINE.equals(interfaceInfoStatusEnum)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口已禁用");
        }

        interfaceInfo.setId(id);
        interfaceInfo.setStatus(0);

        boolean isUpdate = interfaceInfoService.updateById(interfaceInfo);
        if (!isUpdate) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        return ResultUtils.success(true);
    }


    @PostMapping("/invoke")
    public BaseResponse<Object> invokeInterfaceInfo(@RequestBody InterfaceInfoInvokeRequest interfaceInfoInvokeRequest, HttpServletRequest request) {
        if (interfaceInfoInvokeRequest == null || interfaceInfoInvokeRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        long id = interfaceInfoInvokeRequest.getId();
        String userRequestParams = interfaceInfoInvokeRequest.getUserRequestParams();

        InterfaceInfo interfaceInfo = interfaceInfoService.getById(id);

        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        Integer status = interfaceInfo.getStatus();
        String url = interfaceInfo.getUrl();

        InterfaceInfoStatusEnums interfaceInfoStatusEnum = InterfaceInfoStatusEnums.getEnumByCode(status);
        if (InterfaceInfoStatusEnums.OFFLINE.equals(interfaceInfoStatusEnum)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口已关闭");
        }
        if (InterfaceInfoStatusEnums.FORBID.equals(interfaceInfoStatusEnum)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口已禁用");
        }

        User loginUser = userService.getLoginUser(request);
        String accessKey = loginUser.getAccessKey();
        String secretKey = loginUser.getSecretKey();

        OpenApiClient openApiClient = new OpenApiClient(accessKey, secretKey);
        Gson gson = new Gson();
        com.cclu.openapiclientsdk.model.User user = gson.fromJson(userRequestParams, com.cclu.openapiclientsdk.model.User.class);
        String userNameByPost = openApiClient.getUserNameByPost(user);

        return ResultUtils.success(userNameByPost);
    }

}
