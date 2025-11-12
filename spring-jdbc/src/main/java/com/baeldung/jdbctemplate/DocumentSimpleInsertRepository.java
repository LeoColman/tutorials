package com.baeldung.jdbctemplate;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.Map;

public class DocumentSimpleInsertRepository {
    private final SimpleJdbcInsert insert;

    public DocumentSimpleInsertRepository(DataSource dataSource) {
        this.insert = new SimpleJdbcInsert(dataSource).withTableName("documents").usingGeneratedKeyColumns("id");
    }

    public Long save(String filename, String mimeType, byte[] content) {
        Map<String, Object> params = Map.of("filename", filename, "mime_type", mimeType, "content", content);
        Number key = insert.executeAndReturnKey(params);
        return (key != null) ? key.longValue() : null;
    }
}