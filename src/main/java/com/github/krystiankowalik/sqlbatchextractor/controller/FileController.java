package com.github.krystiankowalik.sqlbatchextractor.controller;

import com.github.krystiankowalik.sqlbatchextractor.exception.CustomFileNotFoundException;
import com.github.krystiankowalik.sqlbatchextractor.model.Database;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public interface FileController {

    @GetMapping("/{fileName:.+}")
    ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws CustomFileNotFoundException;


}
