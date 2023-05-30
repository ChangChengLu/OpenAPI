package com.cclu.qqclient.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ChangCheng Lu
 * @date 2023/5/22 22:45
 */
@Data
public class QqOnlineVO implements Serializable {

    private String qq;

    private String message;

    public QqOnlineVO() {
        this(null, null);
    }

    public QqOnlineVO(String qq, String message) {
        this.qq = qq;
        this.message = message;
    }
}
