package com.github.krystiankowalik.sqlbatchextractor.dao;

import com.github.krystiankowalik.sqlbatchextractor.model.ReportingTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportingTasksRepo extends JpaRepository<ReportingTask, Integer> {

    ReportingTask getFirstByStatusEquals(ReportingTask.Status status);
}
