package com.tencent.wxcloudrun.utils;

import org.springframework.beans.BeanUtils;

public class ConvertUtils {

    public static <T> T copyProperties(Class<T> targetClass, Object source) {
        if (source == null) {
            throw new IllegalArgumentException("Source object must not be null");
        }

        T target = BeanUtils.instantiateClass(targetClass);
        // 确保使用 Spring 的 BeanUtils，参数顺序是 (dest, orig)
        BeanUtils.copyProperties(source, target);  // 如果用 Apache Commons，则反过来

        // 调试：打印拷贝后的属性
        System.out.println("Copied target: " + target);
        return target;
    }
}
