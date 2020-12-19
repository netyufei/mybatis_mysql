package com.it.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class MybatisDruidSourceFactory extends UnpooledDataSourceFactory {
    public MybatisDruidSourceFactory() {
        this.dataSource = new DruidDataSource();
    }

}
