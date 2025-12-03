package com.zentidelabs.web.util;

import org.apache.commons.lang3.RandomStringUtils;
import java.text.SimpleDateFormat;

public class RandomUtil {
    public static String randomId() {

        // 18자리 -> aaa202212310142141

        String idCharacter = RandomStringUtils.randomAlphanumeric(3);
        String idNum = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(System.currentTimeMillis());
        return idCharacter + idNum;
    }
}
