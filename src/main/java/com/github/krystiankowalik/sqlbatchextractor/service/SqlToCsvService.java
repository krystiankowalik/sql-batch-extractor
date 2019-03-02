package com.github.krystiankowalik.sqlbatchextractor.service;

import com.opencsv.CSVWriter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@AllArgsConstructor

public class SqlToCsvService {

    public void writeSqlResultToCsv(ResultSet resultSet, String outputFilePath) throws IOException, SQLException {

        try {
            Writer writer = new FileWriter(outputFilePath);
            CSVWriter csvWriter = new CSVWriter(writer);

            csvWriter.writeAll(resultSet, true);
        } finally {
            resultSet.close();

        }
    }


}
