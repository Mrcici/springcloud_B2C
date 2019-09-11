package com.cici.tools;

import java.util.Random;

/**
 * Created by hhbbz on 2018/5/15.
 * @Explain:
 */
public class CaptchaUtils {

    private CaptchaUtils() {
    }

    /**
     * 生成指定位数的验证码
     */
    public static String generateCaptcha(int len) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

}
