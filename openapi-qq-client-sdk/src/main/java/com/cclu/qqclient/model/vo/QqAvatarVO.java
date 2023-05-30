package com.cclu.qqclient.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ChangCheng Lu
 * @date 2023/5/24 13:50
 */
@Data
public class QqAvatarVO implements Serializable {

    private String qq;

    private String alias;

    private String avatar;

    public QqAvatarVO() {
        this(null, null, null);
    }

    public QqAvatarVO(String qq) {
        this(qq, null, null);
    }

    public QqAvatarVO(String  qq, String alias, String avatar) {
        this.qq = qq;
        this.alias = alias;
        this.avatar = avatar;
    }
}
