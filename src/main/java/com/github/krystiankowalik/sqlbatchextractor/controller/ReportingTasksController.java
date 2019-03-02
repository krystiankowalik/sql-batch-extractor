package com.github.krystiankowalik.sqlbatchextractor.controller;

import com.github.krystiankowalik.sqlbatchextractor.exception.CustomFileNotFoundException;
import com.github.krystiankowalik.sqlbatchextractor.model.ReportingTask;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/reportingTasks")
public interface ReportingTasksController {

    @GetMapping("/")
    ResponseEntity<List<ReportingTask>> getAllTasks();

    @PostMapping("/")
    ResponseEntity<ReportingTask> submitTask(@RequestBody ReportingTask reportingTask);


}
