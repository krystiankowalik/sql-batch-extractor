package com.github.krystiankowalik.sqlbatchextractor.service;

import com.github.krystiankowalik.sqlbatchextractor.model.ReportingTask;

import java.util.List;

public interface ReportingTasksService {

    ReportingTask save(ReportingTask reportingTask);

    List<ReportingTask> getAll();

    ReportingTask getFirstTaskOf(ReportingTask.Status status);

    void updateTaskStatus(ReportingTask reportingTask, ReportingTask.Status status);
}
