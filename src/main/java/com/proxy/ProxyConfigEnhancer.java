package com.proxy;

import java.util.HashMap;
import java.util.Map;

public class ProxyConfigEnhancer extends ProxyConfig {

    Map<String, Object> map = new HashMap<>();

    @Override
    public DataSource dataSource() {
        String method = Thread.currentThread() .getStackTrace()[1].getMethodName();
        if (!map.containsKey(method)) {
            map.put(method, super.dataSource());
        }
        return (DataSource) map.get(method);
    }

    @Override
    public JdbcTemplate jdbcTemplate() {
        return super.jdbcTemplate();
    }

    @Override
    public JdbcTemplate jdbcTemplate2() {
        return super.jdbcTemplate2();
    }
}
