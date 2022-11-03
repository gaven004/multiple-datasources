package com.g.mds;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-master")
    public HikariConfig masterConfig() {
        return new HikariConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-slave")
    public HikariConfig slaveConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource masterDataSource(@Qualifier("masterConfig") HikariConfig config) {
        return new HikariDataSource(config);
    }

    @Bean
    public DataSource slaveDataSource(@Qualifier("slaveConfig") HikariConfig config) {
        return new HikariDataSource(config);
    }

    @Bean
    @Primary
    public DataSource routingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                        @Qualifier("slaveDataSource") DataSource slaveDataSource) {

        AbstractRoutingDataSource routingDataSource = new AbstractRoutingDataSource() {
            @Override
            protected DataSource determineTargetDataSource() {
                final DataSource dataSource = super.determineTargetDataSource();
                log.debug("targetDataSource: {}", dataSource);
                return dataSource;
            }

            @Override
            protected Object determineCurrentLookupKey() {
                return DataSourceContextHolder.getDataSource();
            }
        };

        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DataSourceKey.MASTER, masterDataSource);
        dataSourceMap.put(DataSourceKey.SLAVE, slaveDataSource);

        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);

        return routingDataSource;
    }
}
