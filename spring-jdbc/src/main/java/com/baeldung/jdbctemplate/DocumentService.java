package com.baeldung.jdbctemplate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class DocumentService {
    private final DocumentRepositoryLob repo;

    public DocumentService(DocumentRepositoryLob repo) {
        this.repo = repo;
    }

    @Transactional
    public Long storeFile(Path path, String mimeType) throws Exception {
        String filename = path.getFileName().toString();
        long size = Files.size(path);
        try (InputStream is = Files.newInputStream(path)) { // When unknown, cast to int carefully or use bytes[]; many drivers require an int length 
            repo.saveStream(filename, mimeType, is, Math.toIntExact(size));
        } // Optionally return ID if your repository provides it 
        return null; 
    }
}