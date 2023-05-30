package com.cclu.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.openapicommon.model.entity.OrderLock;
import com.cclu.project.service.OrderLockService;
import com.cclu.project.mapper.OrderLockMapper;
import org.springframework.stereotype.Service;

/**
* @author 21237
* @description 针对表【order_lock】的数据库操作Service实现
* @createDate 2023-05-17 19:23:40
*/
@Service
public class OrderLockServiceImpl extends ServiceImpl<OrderLockMapper, OrderLock>
    implements OrderLockService{

}




