package com.github.krystiankowalik.sqlbatchextractor.controller.impl;

import com.github.krystiankowalik.sqlbatchextractor.controller.ReportsController;
import com.github.krystiankowalik.sqlbatchextractor.model.Report;
import com.github.krystiankowalik.sqlbatchextractor.service.ReportsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReportsControllerImpl implements ReportsController {


    private ReportsService reportsService;

    public ResponseEntity<List<Report>> getAll() {
        return new ResponseEntity<>(reportsService.getAll(), HttpStatus.OK);
    }

    public ResponseEntity<Report> add(Report report) {

        return new ResponseEntity<>(reportsService.save(report), HttpStatus.OK);
    }
}
