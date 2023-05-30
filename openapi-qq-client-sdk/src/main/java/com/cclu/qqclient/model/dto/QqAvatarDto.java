package com.cclu.qqclient.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ChangCheng Lu
 * @date 2023/5/24 13:53
 */
@Data
public class QqAvatarDto implements Serializable {

    private int code;

    private String msg;

    private String imgurl;

    private String name;

}
