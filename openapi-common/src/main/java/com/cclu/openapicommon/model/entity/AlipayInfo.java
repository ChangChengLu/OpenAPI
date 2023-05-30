package com.cclu.openapicommon.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName alipay_info
 */
@TableName(value ="alipay_info")
@Data
public class AlipayInfo implements Serializable {
    /**
     * 订单id
     */
    @TableId
    private String orderSn;

    /**
     * 主键
     */
    private Long id;

    /**
     * 交易名称
     */
    private String tradeName;

    /**
     * 交易金额
     */
    private Double totalAmount;

    /**
     * 买家付款金额
     */
    private Double buyerPayAmount;

    /**
     * 买家在支付宝的唯一id
     */
    private String buyerId;

    /**
     * 支付宝交易凭证号
     */
    private String tradeNo;

    /**
     * 交易状态
     */
    private String status;

    /**
     * 买家付款时间
     */
    private Date gmtPayment;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}