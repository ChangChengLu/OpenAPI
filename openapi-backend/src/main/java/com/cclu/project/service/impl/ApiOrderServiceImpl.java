package com.cclu.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.openapicommon.model.entity.ApiOrder;
import com.cclu.project.service.ApiOrderService;
import com.cclu.project.mapper.ApiOrderMapper;
import org.springframework.stereotype.Service;

/**
* @author 21237
* @description 针对表【api_order】的数据库操作Service实现
* @createDate 2023-05-17 19:23:40
*/
@Service
public class ApiOrderServiceImpl extends ServiceImpl<ApiOrderMapper, ApiOrder>
    implements ApiOrderService{

}




