package com.github.krystiankowalik.sqlbatchextractor.service;

import com.github.krystiankowalik.sqlbatchextractor.model.Database;

import java.util.List;

public interface DatabaseService {
     Database save(Database database);

     List<Database> getAll();
}
