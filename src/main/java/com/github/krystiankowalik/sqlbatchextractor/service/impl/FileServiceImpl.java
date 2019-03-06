package com.github.krystiankowalik.sqlbatchextractor.service.impl;

import com.github.krystiankowalik.sqlbatchextractor.config.FileStorageProperties;
import com.github.krystiankowalik.sqlbatchextractor.exception.CustomFileNotFoundException;
import com.github.krystiankowalik.sqlbatchextractor.exception.FileStorageException;
import com.github.krystiankowalik.sqlbatchextractor.service.DownloadService;
import com.github.krystiankowalik.sqlbatchextractor.service.FileNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class FileServiceImpl implements DownloadService, FileNameService {

    private final Path fileStorageLocation;

    @Autowired
    public FileServiceImpl(FileStorageProperties fileStorageProperties) throws FileStorageException {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName) throws CustomFileNotFoundException {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new CustomFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new CustomFileNotFoundException("File not found " + fileName, ex);
        }
    }


    @Override
    public String getNewUniqueName(String prefix) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm_ss");
        String uniqueId = UUID.randomUUID().toString();
        return prefix + "_" + LocalDateTime.now().format(dateTimeFormatter) + "(" + uniqueId + ")" + ".csv";
    }

    @Override
    public boolean exists(String fullFilePath) {
        return Files.exists(Paths.get(fullFilePath));
    }
}