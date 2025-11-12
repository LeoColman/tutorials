package com.baeldung.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.LobHandler;

import java.io.InputStream;
import java.sql.Types;

public class DocumentRepositoryLob {
    private final NamedParameterJdbcTemplate namedJdbc;
    private final LobHandler lobHandler;

    public DocumentRepositoryLob(JdbcTemplate jdbc, LobHandler lobHandler) {
        this.namedJdbc = new NamedParameterJdbcTemplate(jdbc);
        this.lobHandler = lobHandler;
    }

    public int saveBytes(String filename, String mimeType, byte[] content) {
        String sql = """
                 INSERT INTO documents (filename, mime_type, content) VALUES (:filename, :mimeType, :content) """;
        var params = new MapSqlParameterSource().addValue("filename", filename).addValue("mimeType", mimeType).addValue("content", new SqlLobValue(content, lobHandler), Types.BLOB);
        return namedJdbc.update(sql, params);
    }

    public int saveStream(String filename, String mimeType, InputStream contentStream, int contentLength) {
        String sql = """
                INSERT INTO documents (filename, mime_type, content) VALUES (:filename, :mimeType, :content) """;
        var params = new MapSqlParameterSource().addValue("filename", filename).addValue("mimeType", mimeType).addValue("content", new SqlLobValue(contentStream, contentLength, lobHandler), Types.BLOB);
        return namedJdbc.update(sql, params);
    }
}