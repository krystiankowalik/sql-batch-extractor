package com.github.krystiankowalik.sqlbatchextractor.controller.impl;

import com.github.krystiankowalik.sqlbatchextractor.controller.DatabasesController;
import com.github.krystiankowalik.sqlbatchextractor.model.Database;
import com.github.krystiankowalik.sqlbatchextractor.service.DatabaseService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class DatabasesControllerImpl implements DatabasesController {

    private DatabaseService databasesService;

    public ResponseEntity<List<Database>> getAllDatabases() {
        return new ResponseEntity<>(databasesService.getAll(), HttpStatus.OK);
    }

    public ResponseEntity<Database> addDatabase(Database database) {
        log.info("Saving " + database);
        return new ResponseEntity<>(databasesService.save(database), HttpStatus.OK);
    }
}
