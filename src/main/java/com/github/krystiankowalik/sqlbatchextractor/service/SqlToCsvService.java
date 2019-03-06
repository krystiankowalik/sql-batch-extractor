package com.github.krystiankowalik.sqlbatchextractor.service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface SqlToCsvService {
    void writeSqlResultToCsv(ResultSet resultSet, String outputFilePath) throws IOException, SQLException;
}
