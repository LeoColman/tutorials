package com.baeldung.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class DocumentRepository {
    private final JdbcTemplate jdbc;

    public DocumentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public int saveBytes(String filename, String mimeType, byte[] content) {
        String sql = "INSERT INTO documents (filename, mime_type, content) VALUES (?, ?, ?)";
        return jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, filename);
            ps.setString(2, mimeType);
            ps.setBytes(3, content); // uses setBytes under the hood 
            return ps;
        });
    }
}