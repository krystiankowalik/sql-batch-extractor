package com.github.krystiankowalik.sqlbatchextractor.service;

import com.github.krystiankowalik.sqlbatchextractor.exception.CustomFileNotFoundException;
import org.springframework.core.io.Resource;

public interface DownloadService {
    Resource loadFileAsResource(String fileName) throws CustomFileNotFoundException;
}
