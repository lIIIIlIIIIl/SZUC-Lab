package com.imhereaaaa.client.pkg.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

public class ModelAttrChecker {
    private static final Logger log = LogManager.getLogger(ModelAttrChecker.class);

    public static boolean isAllNonNull(Object bean) {
        if (bean == null) {
            throw new NullPointerException("bean is null");
        }

        Class<?> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(bean);
                if (value == null) {
                    log.error("{} is null!", field.getName());
                    return false;
                }
            } catch (IllegalAccessException e) {
                return false;
            }
        }

        return true;
    }
}
