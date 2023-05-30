package com.cclu.qqclient.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import com.cclu.qqclient.common.BaseResponse;
import com.cclu.qqclient.model.dto.QqAvatarDto;
import com.cclu.qqclient.model.dto.QqOnlineDto;
import com.cclu.qqclient.model.vo.QqAvatarVO;
import com.cclu.qqclient.model.vo.QqOnlineVO;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import static com.cclu.qqclient.utils.SignUtils.genSign;

/**
 * @author ChangCheng Lu
 * @date 2023/5/22 22:49
 */
public class QqClient {

    private static final String HOST_NAME = "https://api.btstu.cn";

    private String accessKey;

    private String secretKey;

    private final Gson gson = new Gson();

    public QqClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public BaseResponse<QqOnlineVO> getOnlineStatus(String qq) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("qq", qq);
        String result = HttpUtil.get(HOST_NAME + "qqol/api.php", paramMap);
        QqOnlineDto qqOnlineDto = gson.fromJson(result, QqOnlineDto.class);
        int code = qqOnlineDto.getCode();
        String msg = qqOnlineDto.getMsg();

        QqOnlineVO qqOnlineVO = new QqOnlineVO();
        qqOnlineVO.setQq(qq);
        qqOnlineVO.setMessage(msg);

        return new BaseResponse<QqOnlineVO>(code, msg);
    }

    public BaseResponse<QqAvatarVO> getAliasAndAvatar(String qq) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("qq", qq);
        String result = HttpUtil.get(HOST_NAME + "qqxt/api.php", paramMap);
        QqAvatarDto qqAvatarDto = gson.fromJson(result, QqAvatarDto.class);
        int code = qqAvatarDto.getCode();
        String msg = qqAvatarDto.getMsg();
        String imgurl = qqAvatarDto.getImgurl();
        String name = qqAvatarDto.getName();

        QqAvatarVO qqAvatarVO = new QqAvatarVO();
        qqAvatarVO.setQq(qq);
        qqAvatarVO.setAlias(name);
        qqAvatarVO.setAvatar(imgurl);

        return new BaseResponse<QqAvatarVO>(code, qqAvatarVO, msg);
    }

    private Map<String, Object> getHeaderMap(String body) {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", genSign(body, secretKey));
        return hashMap;
    }

}
