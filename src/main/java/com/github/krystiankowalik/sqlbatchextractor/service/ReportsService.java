package com.github.krystiankowalik.sqlbatchextractor.service;

import com.github.krystiankowalik.sqlbatchextractor.model.Report;

import java.util.List;

public interface ReportsService {
    Report save(Report report);

    Report get(Integer id);

    List<Report> getAll();
}
