package com.github.krystiankowalik.sqlbatchextractor.service;

import com.github.krystiankowalik.sqlbatchextractor.model.ReportingTask;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SqlFetchingService {

    ResultSet fetchResultSet(ReportingTask reportingTask) throws SQLException;
}
