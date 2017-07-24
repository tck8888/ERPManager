package com.tck.erpmanager;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;

/**
 * Created by tck on 2017/7/23.
 */

public class UpImageUtils {

    private String endpoint = "http://oss-cn-shanghai.aliyuncs.com";

    private String OSS_AccessKeyId="LTAIcmokAJfitWW0";
    private String OSS_SecretKeyId="cSHauhT5I7tR70CkY05Pza1DosVAn9";

    private String OSS_AccessKeyId1="LTAIrgalwNs26Zjd";
    private String OSS_SecretKeyId1="q2tkQpkZsDadOJEzxc5eYXfv0MQi2X";

    private String test="RoleArn: acs:ram::1306393819997867:role/aliyunosstokengeneratorrole";

    private static volatile UpImageUtils singleton;

    private UpImageUtils() {
    }

    public static UpImageUtils getInstance() {
        if (singleton == null) {
            synchronized (UpImageUtils.class) {
                if (singleton == null) {
                    singleton = new UpImageUtils();
                }
            }
        }
        return singleton;
    }


    public void test() {
        // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考访问控制章节
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(OSS_AccessKeyId, OSS_SecretKeyId, "<StsToken.SecurityToken>");

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次

        OSS oss = new OSSClient(ERPApp.getContext(), endpoint, credentialProvider,conf);

    }
}
