package org.after90.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by zhaogj on 19/06/2017.
 */
@Slf4j
public class MemoryUtils {

    public static boolean isLow() {
        Runtime runTime = Runtime.getRuntime();
        long lTotal = runTime.totalMemory();
        long lFree = runTime.freeMemory();
        return (lFree * 100L / lTotal) <= 10;
    }

    public static boolean isLow(int nPercent) {
        Runtime runTime = Runtime.getRuntime();
        long lTotal = runTime.totalMemory();
        long lFree = runTime.freeMemory();
        return (lFree * 100L / lTotal) <= nPercent;
    }

    public static boolean freeMemoryLow(long lM) {
        return (Runtime.getRuntime()).freeMemory() <= (lM * 1024L * 1024L);
    }

}
