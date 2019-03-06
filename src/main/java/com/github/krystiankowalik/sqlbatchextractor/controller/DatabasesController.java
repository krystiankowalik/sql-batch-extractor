package com.github.krystiankowalik.sqlbatchextractor.controller;

import com.github.krystiankowalik.sqlbatchextractor.model.Database;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/databases")
public interface DatabasesController {

    @GetMapping("/")
    ResponseEntity<List<Database>> getAllDatabases();


    @PostMapping("/")
     ResponseEntity<Database> addDatabase(@RequestBody Database database);

}
