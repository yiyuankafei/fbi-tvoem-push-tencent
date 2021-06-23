package com.firstbrave.fbi.tvoem.tencent.request;

import com.firstbrave.fbi.tvoem.tencent.request.base.BaseRequest;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class AccessTokenRequest extends BaseRequest {

    /**
     * 访问密钥,使用 appkey 对字符串 'appid+appkey' 进行 AES 得到
     */
    private String encryptionKey;
}
