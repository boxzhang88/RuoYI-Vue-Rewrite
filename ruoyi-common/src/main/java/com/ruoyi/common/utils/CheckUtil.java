package com.ruoyi.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * @author ZhangC
 * date 2023/25/5 14:26
 * IntelliJ IDEA
 */
public class CheckUtil {

    public static boolean isNull(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String) {
            return StringUtils.isBlank((String)obj);
        } else if (obj instanceof Collection) {
            return ((Collection)obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map)obj).isEmpty();
        } else if (obj instanceof Object[]) {
            return ((Object[])obj).length == 0;
        } else {
            return false;
        }
    }

    public static boolean notNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean isNulls(Object...objects) {
        if (null == objects || objects.length == 0){
            return true;
        }
        return Arrays.stream(objects).anyMatch((x)->isNull(x)?true:false);
    }
}
