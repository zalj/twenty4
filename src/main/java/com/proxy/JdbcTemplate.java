package com.proxy;

public class JdbcTemplate {
    DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
