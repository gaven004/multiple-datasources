package com.g.mds;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataSourceContextHolder {
    private static ThreadLocal<DataSourceKey> datasourceContext = new ThreadLocal<>();

    public static void setDataSource(DataSourceKey key) {
        log.debug("setDataSource: {}", key);
        datasourceContext.set(key);
    }

    public static DataSourceKey getDataSource() {
        final DataSourceKey key = datasourceContext.get();
        log.debug("getDataSource: {}", key);
        return key;
    }

    public static void clear() {
        datasourceContext.remove();
    }
}
