package com.cclu.openapicommon.model.enums;

import com.cclu.openapicommon.commons.ErrorCode;
import com.cclu.openapicommon.exception.BusinessException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 接口信息状态枚举
 *
 * @author cclu
 */
public enum ApiOrderStatusEnums {

    /**
     * 是否删除(0-未删, 1-已删)
     */
    OFFLINE(0, "上线"),
    ONLINE(1, "下线"),
    FORBID(2, "禁用");

    private final int code;

    private final String message;


    ApiOrderStatusEnums(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public static ApiOrderStatusEnums getEnumByCode(Integer code) {
        if (code == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        ApiOrderStatusEnums[] interfaceInfoStatusEnums = ApiOrderStatusEnums.values();
        for (ApiOrderStatusEnums interfaceInfoStatusEnum : interfaceInfoStatusEnums) {
            if (interfaceInfoStatusEnum.getCode() == code) {
                return interfaceInfoStatusEnum;
            }
        }

        return null;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.code).collect(Collectors.toList());
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
