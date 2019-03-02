package com.github.krystiankowalik.sqlbatchextractor.controller;

import com.github.krystiankowalik.sqlbatchextractor.model.Report;
import com.github.krystiankowalik.sqlbatchextractor.model.ReportingTask;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public interface ReportsController {


    @GetMapping("/")
    ResponseEntity<List<Report>> getAll();

    @PostMapping("/")

    ResponseEntity<Report> add(@RequestBody Report report);


}
