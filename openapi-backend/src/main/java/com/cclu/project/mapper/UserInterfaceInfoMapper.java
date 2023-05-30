package com.cclu.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cclu.openapicommon.model.entity.UserInterfaceInfo;

import java.util.List;

/**
* @author 21237
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Mapper
* @createDate 2023-05-07 08:49:36
* @Entity com.cclu.project.model.entity.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);

}




