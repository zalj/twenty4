package com.proxy;

public class ProxyMain {
    public static void main(String[] args) {
        ProxyConfig proxyConfig = new ProxyConfig();
        JdbcTemplate jdbcTemplate = proxyConfig.jdbcTemplate();
        JdbcTemplate jdbcTemplate2 = proxyConfig.jdbcTemplate2();

        System.out.println(jdbcTemplate.dataSource == jdbcTemplate2.dataSource);

        ProxyConfigEnhancer proxyConfigEnhancer = new ProxyConfigEnhancer();
        jdbcTemplate = proxyConfigEnhancer.jdbcTemplate();
        jdbcTemplate2 = proxyConfigEnhancer.jdbcTemplate2();

        System.out.println(jdbcTemplate.dataSource == jdbcTemplate2.dataSource);
    }
}
