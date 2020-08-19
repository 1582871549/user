package com.meng.user.common.util;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于BeanCopier的属性拷贝
 *
 * @author dujianwei
 * @create 2020-05-26
 */
public class BeanCopyUtil {

    /**
     * 创建过的BeanCopier实例放到缓存中，下次可以直接获取，提升性能
     */
    private static final Map<String, BeanCopier> BEAN_COPIER_MAP = new ConcurrentHashMap<>();

    private static final BeanCopier FAILBACK = new BeanCopier() {

        @Override
        public void copy(Object source, Object target, Converter converter) {
            BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
        }

        private String[] getNullPropertyNames(Object source) {

            final BeanWrapper src = new BeanWrapperImpl(source);
            PropertyDescriptor[] pds = src.getPropertyDescriptors();

            Set<String> emptyNames = new HashSet<>();
            for (PropertyDescriptor pd : pds) {
                Object srcValue = src.getPropertyValue(pd.getName());
                if (srcValue == null) {
                    emptyNames.add(pd.getName());
                }
            }
            String[] result = new String[emptyNames.size()];
            return emptyNames.toArray(result);
        }

    };


    /**
     * @param source 来源对象
     * @param target 目标类,要求该类必须有无参构造函数
     * @return s
     */
    public static <S, T> T copy(S source, Class<T> target) {
        requireNonNull(source, target);
        return copy(source, newInstance(target));
    }

    public static <S, T> List<T> copyList(List<S> sourceList, Class<T> targetClass) {

        if (CollectionUtils.isEmpty(sourceList)) {
            return Collections.emptyList();
        }

        List<T> targetList = new ArrayList<>(sourceList.size());

        sourceList.forEach(source -> {
            targetList.add(copy(source, targetClass));
        });

        return targetList;
    }

    private static <S, T> void requireNonNull(S source, T target) {
        Objects.requireNonNull(source, "源对象不能为空");
        Objects.requireNonNull(target, "目标对象不能为空");
    }

    private static <T> T newInstance(Class<T> target) {
        try {
            return target.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("instance creation failed while copying bean");
        }
    }

    /**
     * 该方法没有自定义Converter,简单进行常规属性拷贝
     *
     * @param source 来源对象
     * @param target 目标对象
     */
    public static <S, T> T copy(final S source, final T target) {

        requireNonNull(source, target);

        Class<?> sourceClazz = source.getClass();
        Class<?> targetClazz = target.getClass();

        String key = generateKey(sourceClazz, targetClazz);

        BeanCopier beanCopier = BEAN_COPIER_MAP.get(key);

        if (beanCopier == null) {

            if (sourceClazz.getClassLoader() == targetClazz.getClassLoader()) {
                beanCopier = BeanCopier.create(sourceClazz, targetClazz, false);
            } else {
                beanCopier = FAILBACK;
            }

            BEAN_COPIER_MAP.put(key, beanCopier);
        }

        beanCopier.copy(source, target, null);

        return target;
    }


    /**
     * 生成key
     *
     * @param sourceClazz 源文件
     * @param targetClazz 目标文件
     * @return key
     */
    private static String generateKey(Class<?> sourceClazz, Class<?> targetClazz) {
        return sourceClazz.getName() + targetClazz.getName();
    }
}
