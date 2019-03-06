package com.github.krystiankowalik.sqlbatchextractor.service;

public interface FileNameService {

    String getNewUniqueName(String prefix);

    boolean exists(String fullFilePath);
}
