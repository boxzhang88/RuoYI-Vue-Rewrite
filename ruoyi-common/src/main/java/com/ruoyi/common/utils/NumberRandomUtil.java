package com.ruoyi.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author ZhangC
 * date 2024/6/9 23:06
 * IntelliJ IDEA
 */
public class NumberRandomUtil {
    public static BigDecimal getRandomNumber() {
        double min = 1.00;
        double max = 20.00;
        double randomDouble = Math.random() * (max - min) + min;
        BigDecimal randomBigDecimal = new BigDecimal(randomDouble).setScale(2, RoundingMode.HALF_UP);
        return randomBigDecimal;
    }
}
