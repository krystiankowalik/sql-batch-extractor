package com.github.krystiankowalik.sqlbatchextractor.controller.impl;

import com.github.krystiankowalik.sqlbatchextractor.controller.FileController;
import com.github.krystiankowalik.sqlbatchextractor.exception.CustomFileNotFoundException;
import com.github.krystiankowalik.sqlbatchextractor.service.DownloadService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class FileControllerImpl implements FileController {

    private DownloadService downloadService;

    public ResponseEntity<Resource> downloadFile( String fileName, HttpServletRequest request) throws CustomFileNotFoundException {

        log.info("Trying to download file: " + fileName);
        // Load file as Resource
        Resource resource = downloadService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    
}
