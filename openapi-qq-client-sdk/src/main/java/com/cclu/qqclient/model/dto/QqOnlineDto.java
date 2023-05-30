package com.cclu.qqclient.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ChangCheng Lu
 * @date 2023/5/22 22:53
 */
@Data
public class QqOnlineDto implements Serializable {

    private int code;

    private String msg;
}
