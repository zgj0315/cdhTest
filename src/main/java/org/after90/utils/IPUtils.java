package org.after90.utils;

import lombok.extern.slf4j.Slf4j;


/**
 * Created by zhaogj on 06/07/2017.
 */
@Slf4j
public class IPUtils {

    //字符型ip转为整型
    public static long strToLong(String strIP) {
        long lIP = -1;
        try {
            String[] astrIP = strIP.split("\\.");
            lIP = (Long.parseLong(astrIP[0]) << 24) + (Long.parseLong(astrIP[1]) << 16)
                    + (Long.parseLong(astrIP[2]) << 8) + Long.parseLong(astrIP[3]);
        } catch (Exception e) {
            log.error("invalid ip:{}", strIP, e);
        }
        return lIP;
    }

    //整型ip转为字符型
    public static String longToStr(long lIP) {
        StringBuffer sb = new StringBuffer();
        sb.append(String.valueOf((lIP >>> 24)));
        sb.append(".");
        sb.append(String.valueOf(((lIP & 0x00FFFFFF) >>> 16)));
        sb.append(".");
        sb.append(String.valueOf(((lIP & 0x0000FFFF) >>> 8)));
        sb.append(".");
        sb.append(String.valueOf(((lIP & 0x000000FF))));
        return sb.toString();
    }


    public static String getRandom() {
        long lIP = (long) (Math.random() * 4294967295L);
        return longToStr(lIP);
    }
}
