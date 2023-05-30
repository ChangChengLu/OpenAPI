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
public enum UserInterfaceInfoStatusEnums {

    /**
     * 是否删除(0-未删, 1-已删)
     */
    OFFLINE(0, "禁用"),
    ONLINE(1, "启动");

    private final int code;

    private final String message;

    UserInterfaceInfoStatusEnums(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public static UserInterfaceInfoStatusEnums getEnumByCode(Integer code) {
        if (code == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        UserInterfaceInfoStatusEnums[] interfaceInfoStatusEnums = UserInterfaceInfoStatusEnums.values();
        for (UserInterfaceInfoStatusEnums interfaceInfoStatusEnum : interfaceInfoStatusEnums) {
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
