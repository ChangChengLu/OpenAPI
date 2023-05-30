package com.cclu.openapicommon.service;


import com.cclu.openapicommon.model.entity.User;

/**
 * 用户服务
 *
 * @author cclu
 */
public interface InnerUserService {

    /**
     * 数据库中查是否已分配给用户秘钥（accessKey）
     * @param accessKey
     * @return
     */
    User getInvokeUser(String accessKey);
}
