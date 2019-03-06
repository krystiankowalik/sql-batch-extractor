package com.github.krystiankowalik.sqlbatchextractor.service.impl;

import com.github.krystiankowalik.sqlbatchextractor.service.SqlToCsvService;
import com.univocity.parsers.csv.CsvRoutines;
import com.univocity.parsers.csv.CsvWriterSettings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;

@Service
@AllArgsConstructor
public class SqlToCsvServiceImpl implements SqlToCsvService {

    public void writeSqlResultToCsv(ResultSet resultSet, String outputFilePath) throws IOException {

        try (Writer writer = new FileWriter(outputFilePath)) {
            CsvWriterSettings csvWriterSettings = new CsvWriterSettings();
            csvWriterSettings.setHeaderWritingEnabled(true);
            CsvRoutines csvRoutines = new CsvRoutines(csvWriterSettings);
            csvRoutines.write(resultSet, writer);
        }


    }

}
