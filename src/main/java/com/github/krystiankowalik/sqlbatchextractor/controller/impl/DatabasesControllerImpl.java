package com.github.krystiankowalik.sqlbatchextractor.controller.impl;

import com.github.krystiankowalik.sqlbatchextractor.controller.DatabasesController;
import com.github.krystiankowalik.sqlbatchextractor.model.Database;
import com.github.krystiankowalik.sqlbatchextractor.service.DatabasesService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Log4j2
public class DatabasesControllerImpl implements DatabasesController {


    private DatabasesService databasesService;

    public ResponseEntity<List<Database>> getAllQueries() {
        return new ResponseEntity<>(databasesService.getAll(), HttpStatus.OK);
    }

    public ResponseEntity<Database> addQuery( Database database) {
        log.info("Saving " + database);
        return new ResponseEntity<>(databasesService.save(database), HttpStatus.OK);
    }
}
