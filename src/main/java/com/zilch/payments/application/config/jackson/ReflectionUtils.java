package com.zilch.payments.application.config.jackson;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("checkstyle:hideutilityclassconstructor")
public class ReflectionUtils {

    public static <T> Set<Class<? extends T>> getSubTypesOf(Class<T> clazz) {
        return getSubTypesOf(clazz, clazz.getPackageName());
    }

    public static <T> Set<Class<? extends T>> getSubTypesOf(Class<T> clazz, String packageName) {
        var reflections = new Reflections(packageName, new SubTypesScanner(false));
        return reflections.getSubTypesOf(clazz);
    }

}
