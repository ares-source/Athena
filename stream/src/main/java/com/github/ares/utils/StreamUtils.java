package com.github.ares.utils;

import com.google.common.collect.Iterables;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

public class StreamUtils {

    /**
     * 批量并行处理Function（）大集合，并将返回值统一交给consumer处理
     *
     * @param list
     * @param batchSize
     * @param func
     * @param consumer
     * @param <T>
     * @param <R>
     */
    public static <T, R> void batchProcess(Collection<T> list, int batchSize, Function<Collection<T>, R> func, Consumer<R> consumer) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        if (CollectionUtils.size(list) <= batchSize) {
            consumer.accept(func.apply(list));
            return;
        }
        Iterables.partition(list, batchSize).forEach(keys -> consumer.accept(func.apply(keys)));
    }

}
