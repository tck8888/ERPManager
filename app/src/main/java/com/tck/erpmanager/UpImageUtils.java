package com.tck.erpmanager;



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

}
