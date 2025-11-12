package com.baeldung.jdbctemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
@SpringBootConfiguration
@Import({DocumentRepository.class})
class DocumentRepositoryTest {
    @Autowired
    private DocumentRepository repo;
    @Autowired
    private JdbcTemplate jdbc;

    @Test
    void whenInsertBytes_thenRowInserted() {
        byte[] bytes = "hello blob".getBytes(StandardCharsets.UTF_8);
        int updated = repo.saveBytes("greeting.txt", "text/plain", bytes);
        assertThat(updated).isEqualTo(1);
        Integer count = jdbc.queryForObject("SELECT COUNT(*) FROM documents", Integer.class);
        assertThat(count).isEqualTo(1);
    }
}