package com.cclu.openapiorder.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.openapicommon.commons.BaseResponse;
import com.cclu.openapicommon.model.entity.ApiOrder;
import com.cclu.openapiorder.mapper.ApiOrderMapper;
import com.cclu.openapiorder.model.dto.ApiOrderCancelDto;
import com.cclu.openapiorder.model.dto.ApiOrderDto;
import com.cclu.openapiorder.model.dto.ApiOrderStatusInfoDto;
import com.cclu.openapiorder.model.vo.ApiOrderStatusVo;
import com.cclu.openapiorder.model.vo.OrderSnVo;
import com.cclu.openapiorder.service.ApiOrderService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* @author 21237
* @description 针对表【api_order】的数据库操作Service实现
* @createDate 2023-05-17 19:23:40
*/
@Service
public class ApiOrderServiceImpl extends ServiceImpl<ApiOrderMapper, ApiOrder>
    implements ApiOrderService {

    @Override
    public BaseResponse<OrderSnVo> generateOrderSn(ApiOrderDto apiOrderDto, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public BaseResponse generateToken(Long id, HttpServletResponse response) {
        return null;
    }

    @Override
    public BaseResponse cancelOrderSn(ApiOrderCancelDto apiOrderCancelDto, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public void orderPaySuccess(String orderSn) {

    }

    @Override
    public BaseResponse<Page<ApiOrderStatusVo>> getCurrentOrderInfo(ApiOrderStatusInfoDto statusInfoDto, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponse getOrderEchartsData(List<String> dateList) {
        return null;
    }
}




