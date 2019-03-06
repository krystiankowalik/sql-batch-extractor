package com.github.krystiankowalik.sqlbatchextractor.controller.impl;

import com.github.krystiankowalik.sqlbatchextractor.controller.ReportingTasksController;
import com.github.krystiankowalik.sqlbatchextractor.model.ReportingTask;
import com.github.krystiankowalik.sqlbatchextractor.service.ReportingTasksService;
import com.github.krystiankowalik.sqlbatchextractor.service.ReportsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReportingTasksControllerImpl implements ReportingTasksController {


    private ReportingTasksService reportingTasksService;
    private ReportsService reportsService;

    public ResponseEntity<List<ReportingTask>> getAllTasks() {
        return new ResponseEntity<>(reportingTasksService.getAll(), HttpStatus.OK);
    }

    public ResponseEntity<ReportingTask> submitTask(@RequestBody ReportingTask reportingTask) {
        reportingTask.setStatus(ReportingTask.Status.PENDING);
        reportingTask.setResourceLink(null);
        reportingTask.setReport(reportsService.get(reportingTask.getReport().getId()));
        ReportingTask submitted = reportingTasksService.save(reportingTask);
        return new ResponseEntity<>(submitted, HttpStatus.ACCEPTED);
    }
}
