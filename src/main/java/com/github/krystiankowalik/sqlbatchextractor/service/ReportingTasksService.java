package com.github.krystiankowalik.sqlbatchextractor.service;

import com.github.krystiankowalik.sqlbatchextractor.dao.ReportingTasksRepo;
import com.github.krystiankowalik.sqlbatchextractor.model.ReportingTask;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Log4j2
public class ReportingTasksService {


    private ReportingTasksRepo reportingTasksRepo;

    public ReportingTask save(ReportingTask reportingTask) {
        return reportingTasksRepo.save(reportingTask);

    }

    public List<ReportingTask> getAll() {
        return reportingTasksRepo.findAll();
    }

    public ReportingTask getNextTask(ReportingTask.Status status) {
        return reportingTasksRepo.getFirstByStatusEquals(status);
    }

    public void updateTaskStatus(ReportingTask reportingTask, ReportingTask.Status status) {
        ReportingTask.Status initialStatus = reportingTask.getStatus();
        reportingTask.setStatus(status);
        save(reportingTask);
        log.info("Status of task with id: " + reportingTask.getId() + " changed from " + initialStatus + " to " + status);
    }
}
