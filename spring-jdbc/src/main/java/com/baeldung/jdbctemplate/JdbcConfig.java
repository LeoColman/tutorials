package com.baeldung.jdbctemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

@Configuration
public class JdbcConfig {
    @Bean
    public LobHandler lobHandler() { 
        // DefaultLobHandler works well across drivers; detects native behavior when available
        return new DefaultLobHandler(); 
    } 
}