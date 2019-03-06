package com.github.krystiankowalik.sqlbatchextractor.service.impl;

import com.github.krystiankowalik.sqlbatchextractor.model.Database;
import com.github.krystiankowalik.sqlbatchextractor.model.ReportingTask;
import com.github.krystiankowalik.sqlbatchextractor.service.SqlFetchingService;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class SqlFetchingServiceImpl implements SqlFetchingService {


    public ResultSet fetchResultSet(ReportingTask reportingTask) throws SQLException {
        Database db = reportingTask.getReport().getDatabase();

        Connection connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
        Statement statement = connection.createStatement();

        return statement.executeQuery(reportingTask.getReport().getQuery());


    }
}
