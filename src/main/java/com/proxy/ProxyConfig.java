package com.proxy;

public class ProxyConfig {
    public DataSource dataSource() {
        return new DataSource();
    }

    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    public JdbcTemplate jdbcTemplate2() {
        return new JdbcTemplate(dataSource());
    }
}
